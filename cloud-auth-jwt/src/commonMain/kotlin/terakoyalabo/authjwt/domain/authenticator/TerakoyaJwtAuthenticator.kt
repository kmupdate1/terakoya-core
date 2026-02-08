package terakoyalabo.authjwt.domain.authenticator

import terakoyalabo.authjwt.domain.authenticator.model.TerakoyaAuthenticator
import terakoyalabo.authjwt.core.TerakoyaJwtPrincipal

interface TerakoyaJwtAuthenticator<in C : Any, out P : TerakoyaJwtPrincipal?> : TerakoyaAuthenticator<C, P>
