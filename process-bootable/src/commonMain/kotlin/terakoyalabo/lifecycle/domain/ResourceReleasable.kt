package terakoyalabo.lifecycle.domain

interface ResourceReleasable {
    fun release(): Result<Unit>
}
