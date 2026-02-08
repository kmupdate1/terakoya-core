package terakoyalabo.authjwt.domain.authenticator.model

import terakoyalabo.authjwt.domain.principal.model.TerakoyaPrincipal

interface TerakoyaAuthenticator<in C : Any, out P : TerakoyaPrincipal?> {
    suspend fun authenticate(credential: C): P
}
