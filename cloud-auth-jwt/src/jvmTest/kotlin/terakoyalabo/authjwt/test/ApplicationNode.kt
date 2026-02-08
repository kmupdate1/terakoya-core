package terakoyalabo.authjwt.test

import io.ktor.server.auth.AuthenticationConfig
import terakoyalabo.auth.plugin.AuthAdaptable

class ApplicationNode : AuthAdaptable {
    override fun adapt(config: AuthenticationConfig) {
    }
}
