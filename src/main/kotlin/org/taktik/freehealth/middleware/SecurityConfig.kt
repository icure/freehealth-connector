/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware

import org.eclipse.jetty.client.HttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.access.ExceptionTranslationFilter
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.util.matcher.AnyRequestMatcher
import org.taktik.freehealth.middleware.dao.CouchDbProperties
import org.taktik.freehealth.middleware.dao.CouchdbUserDetailsService
import org.taktik.freehealth.middleware.web.Http401UnauthorizedEntryPoint
import org.taktik.freehealth.middleware.web.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun httpClient() = HttpClient().apply { start() }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(8)

    @Bean
    fun userDetailsService(
        passwordEncoder: PasswordEncoder,
        httpClient: HttpClient,
        couchDbProperties: CouchDbProperties,
        authenticationProperties: AuthenticationProperties
    ): CouchdbUserDetailsService = CouchdbUserDetailsService(httpClient, couchDbProperties, authenticationProperties, passwordEncoder)

    @Bean
    fun authenticationProvider(userDetailsService: UserDetailsService): AuthenticationProvider =
        DaoAuthenticationProvider().apply {
            setPasswordEncoder(BCryptPasswordEncoder(8))
            setUserDetailsService(userDetailsService)
        }

    @Bean
    fun authenticationManager(authenticationProvider: AuthenticationProvider): AuthenticationManager =
        ProviderManager(listOf(authenticationProvider))

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        authenticationManager: AuthenticationManager
    ): SecurityFilterChain {
        val loginUrlAuthenticationEntryPoint = LoginUrlAuthenticationEntryPoint("/", mapOf("/api" to "api/login.html"))

        http
            .csrf { it.disable() }
            .addFilterBefore(
                FilterChainProxy(
                    listOf(
                        DefaultSecurityFilterChain(
                            AnyRequestMatcher.INSTANCE,
                            BasicAuthenticationFilter(authenticationManager),
                            ExceptionTranslationFilter(Http401UnauthorizedEntryPoint())
                        )
                    )
                ),
                FilterSecurityInterceptor::class.java
            )
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/login.html").permitAll()
                    .requestMatchers("/api/css/**").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/ws/**").permitAll()
                    .requestMatchers("/**").permitAll()
            }
        return http.build()
    }
}
