package terakoyalabo.lifecycle.domain

interface ResourceVerifiable {
    fun verify(): Result<Unit>
}
