package terakoyalabo.lifecycle.domain

import terakoyalabo.core.domain.primitive.model.ScalarL

interface RetirementNoticable {
    fun onRetire(timeoutMillis: ScalarL): Result<Unit>
}
