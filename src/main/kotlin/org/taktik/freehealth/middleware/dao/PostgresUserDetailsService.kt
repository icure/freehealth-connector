package org.taktik.freehealth.middleware.dao

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.taktik.freehealth.middleware.AuthenticationProperties
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

class PostgresUserDetailsService(
    private val postgresProperties: PostgresProperties,
    private val authenticationProperties: AuthenticationProperties,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService, KeystoreProviderService {

    private val TTL = postgresProperties.cachettl.toLong()
    private val hashedPassword = authenticationProperties.password?.let { passwordEncoder.encode(it) }
    private val cache = ConcurrentHashMap<String, Pair<Long, UserDetails>>()

    private val dataSource = DriverManagerDataSource().apply {
        setDriverClassName("org.postgresql.Driver")
        url = postgresProperties.url
        username = postgresProperties.username
        password = postgresProperties.password
    }

    private val jdbc = JdbcTemplate(dataSource)

    init {
        jdbc.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id                         VARCHAR(36)  PRIMARY KEY,
                password_hash              VARCHAR(255) NOT NULL,
                full_name                  VARCHAR(255),
                mcn_package_name           VARCHAR(255),
                mcn_license                VARCHAR(255),
                mcn_password               VARCHAR(255),
                apb_customer_id            VARCHAR(255),
                apb_password               VARCHAR(255),
                ftm_customer_id            VARCHAR(255),
                ftm_password               VARCHAR(255),
                org_keystore_acc_uuid      VARCHAR(36),
                org_keystore_acc_password  VARCHAR(255),
                org_keystore_prod_uuid     VARCHAR(36),
                org_keystore_prod_password VARCHAR(255)
            )
        """.trimIndent())

        jdbc.execute("""
            CREATE TABLE IF NOT EXISTS user_authorities (
                user_id VARCHAR(36)  NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                role    VARCHAR(100) NOT NULL,
                PRIMARY KEY (user_id, role)
            )
        """.trimIndent())

        jdbc.execute("""
            CREATE TABLE IF NOT EXISTS user_keystores (
                user_id  VARCHAR(36)  NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                key_name VARCHAR(255) NOT NULL,
                data     BYTEA        NOT NULL,
                PRIMARY KEY (user_id, key_name)
            )
        """.trimIndent())
    }

    override fun loadUserByUsername(username: String): UserDetails = cache.computeIfAbsent(username) {
        loadUserInternal(username).let { System.currentTimeMillis() + TTL to it }
    }.let { (time, user) ->
        if (time < System.currentTimeMillis()) {
            try {
                loadUserInternal(username).also { cache[username] = System.currentTimeMillis() + TTL to it }
            } catch (e: IOException) {
                cache[username] = System.currentTimeMillis() + TTL * 10 to user
                user
            } catch (e: Exception) {
                user
            }
        } else user
    }

    private fun loadUserInternal(username: String): UserDetails {
        if (username == authenticationProperties.username) {
            return User(
                _id = authenticationProperties.username,
                passwordHash = hashedPassword ?: "",
                mcnLicense = authenticationProperties.mcnLicense,
                mcnPassword = authenticationProperties.mcnPassword
            )
        }

        val user = try {
            jdbc.queryForObject(
                """SELECT id, password_hash, full_name, mcn_package_name, mcn_license, mcn_password,
                          apb_customer_id, apb_password, ftm_customer_id, ftm_password,
                          org_keystore_acc_uuid, org_keystore_acc_password,
                          org_keystore_prod_uuid, org_keystore_prod_password
                   FROM users WHERE id = ?""",
                arrayOf(username)
            ) { rs, _ ->
                User(
                    _id = rs.getString("id"),
                    passwordHash = rs.getString("password_hash"),
                    fullName = rs.getString("full_name"),
                    mcnPackageName = rs.getString("mcn_package_name"),
                    mcnLicense = rs.getString("mcn_license"),
                    mcnPassword = rs.getString("mcn_password"),
                    apbCustomerId = rs.getString("apb_customer_id"),
                    apbPassword = rs.getString("apb_password"),
                    ftmCustomerId = rs.getString("ftm_customer_id"),
                    ftmPassword = rs.getString("ftm_password"),
                    orgKeystoreAccUuid = rs.getString("org_keystore_acc_uuid"),
                    orgKeystoreAccPassword = rs.getString("org_keystore_acc_password"),
                    orgKeystoreProdUuid = rs.getString("org_keystore_prod_uuid"),
                    orgKeystoreProdPassword = rs.getString("org_keystore_prod_password")
                )
            }
        } catch (e: EmptyResultDataAccessException) {
            throw UsernameNotFoundException("User not found: $username")
        } ?: throw UsernameNotFoundException("User not found: $username")

        val authorities = jdbc.query(
            "SELECT role FROM user_authorities WHERE user_id = ?",
            arrayOf(username)
        ) { rs, _ -> GrantedAuthority(rs.getString("role")) }

        user.authorities.addAll(authorities)
        user.authorities.add(GrantedAuthority("ROLE_USER"))
        return user
    }

    override fun getKeystore(user: User, key: String): ByteArray? {
        val userId = user._id ?: return null
        return try {
            jdbc.queryForObject(
                "SELECT data FROM user_keystores WHERE user_id = ? AND key_name = ?",
                arrayOf(userId, key)
            ) { rs, _ -> rs.getBytes("data") }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}
