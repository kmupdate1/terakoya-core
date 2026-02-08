package terakoyalabo.authjwt.core

import terakoyalabo.authjwt.domain.principal.model.Affiliation
import terakoyalabo.authjwt.domain.principal.model.TerakoyaPrincipal
import terakoyalabo.core.domain.identity.model.HeartBeat
import terakoyalabo.core.domain.identity.model.Identity

data class TerakoyaJwtPrincipal(
    override val identity: Identity,
    override val affiliation: Affiliation,
    override val heartBeat: HeartBeat,
) : TerakoyaPrincipal
