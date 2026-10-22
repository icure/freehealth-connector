package org.taktik.freehealth.middleware.service

import com.nimbusds.oauth2.sdk.TokenResponse
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.domain.sts.IamTokenResult
import java.util.UUID

interface SSOService {
    fun getLegacySSOBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, destination: String? = null, profile: String? = null): BearerToken?
    fun getM2MOauth2Token(tokenId: UUID, keystoreId: UUID, passPhrase: String, cbe: String, kid: String): TokenResponse
    fun exchangeSamlTokenForIamToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, clientId: String, ssin: String? = null, refreshable: Boolean = false): IamTokenResult
}
