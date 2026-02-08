package terakoyalabo.authjwt.plugin

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import terakoyalabo.authjwt.abstruction.model.JwtVerificationConfig
import terakoyalabo.authjwt.abstruction.model.TerakoyaJwtAuthentication
import terakoyalabo.authjwt.core.TerakoyaJwtPrincipal
import terakoyalabo.authjwt.domain.authenticator.TerakoyaJwtAuthenticator

fun AuthenticationConfig.terakoyaJwt(
    name: String? = null,
    config: JwtVerificationConfig,
    authenticator: TerakoyaJwtAuthenticator<JWTCredential, TerakoyaJwtPrincipal?> = TerakoyaJwtAuthentication(),
) {
    jwt(name) {
        verifier(config.verifier)
        validate { credential ->
            // if (/* !condition */) return@validate null

            authenticator.authenticate(credential = credential)
        }
        challenge { defaultScheme, realm ->  }
    }
}
