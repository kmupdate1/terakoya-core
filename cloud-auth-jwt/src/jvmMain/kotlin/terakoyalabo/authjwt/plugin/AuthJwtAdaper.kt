package terakoyalabo.authjwt.plugin

import io.ktor.server.auth.*
import terakoyalabo.auth.plugin.AuthAdaptable

class AuthJwtAdaper : AuthAdaptable {
    override fun adapt(config: AuthenticationConfig) {
    }
}
