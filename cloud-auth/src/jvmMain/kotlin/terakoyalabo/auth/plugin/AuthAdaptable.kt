package terakoyalabo.auth.plugin

import io.ktor.server.auth.AuthenticationConfig

interface AuthAdaptable {
    fun adapt(config: AuthenticationConfig)
}
