package terakoyalabo.authjwt.domain.principal.model

import terakoyalabo.core.domain.identity.model.HeartBeat
import terakoyalabo.core.domain.identity.model.Identity

interface TerakoyaPrincipal {
    val identity: Identity
    val affiliation: Affiliation
    val heartBeat: HeartBeat
}
