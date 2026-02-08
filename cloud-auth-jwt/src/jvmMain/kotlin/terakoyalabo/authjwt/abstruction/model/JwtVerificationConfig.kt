package terakoyalabo.authjwt.abstruction.model

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

data class JwtVerificationConfig(
    val issuer: String,
    val audience: String,
    val secret: String, // from environment propertiy
) {
    internal val verifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withIssuer(issuer)
        .withAudience(audience)
        .build()
}
