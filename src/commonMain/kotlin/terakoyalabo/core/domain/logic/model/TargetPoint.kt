package terakoyalabo.core.domain.logic.model

import terakoyalabo.core.domain.primitive.asLower
import terakoyalabo.core.domain.primitive.model.ScalarD
import terakoyalabo.core.error.LawOfTerakoyaException

data class TargetPoint(val at: ScalarD, val epsilon: ScalarD = ScalarD.MILLI) {
    @Throws(LawOfTerakoyaException::class)
    fun isReachedBy(current: ScalarD): Boolean = epsilon.asLower.isIncludesAndViolatedBy(current = (current - at).abs)
}
