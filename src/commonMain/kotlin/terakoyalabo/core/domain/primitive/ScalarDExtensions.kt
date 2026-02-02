package terakoyalabo.core.domain.primitive

import terakoyalabo.core.domain.primitive.model.ScalarD
import terakoyalabo.core.domain.logic.model.Signum
import terakoyalabo.core.domain.logic.model.TargetPoint
import terakoyalabo.core.domain.logic.model.Threshold
import terakoyalabo.core.error.LawOfTerakoyaException

/**
 * @throws LawOfTerakoyaException
 */
val Threshold.checker: (ScalarD) -> Boolean get() = { !this.isViolatedBy(current = it) }
val Threshold.checkerWithInclusive: (ScalarD) -> Boolean get() = { !this.isIncludesAndViolatedBy(current = it) }

val ScalarD.signum: Signum get() = Signum.of(scalar = this)
val ScalarD.asUpper: Threshold get() = Threshold.upper(limit = this)
val ScalarD.asLower: Threshold get() = Threshold.lower(limit = this)
val ScalarD.asTarget: TargetPoint get() = TargetPoint(at = this)

// --- ScalarD への誘い（理学的な量の抽出） ---
val Int.sVal: ScalarD get() = ScalarD.of(raw = this.toDouble())
val Double.sVal: ScalarD get() = ScalarD.of(raw = this)
