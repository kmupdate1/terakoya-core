package terakoyalabo.authjwt.domain.principal.model

import terakoyalabo.core.domain.identity.model.HeartBeat
import terakoyalabo.core.domain.identity.model.Identity

data class BasePrincipal(
    override val identity: Identity,
    override val affiliation: Affiliation,
    override val heartBeat: HeartBeat,
) : TerakoyaPrincipal
