package terakoyalabo.lifecycle.domain

interface StatePersistant {
    fun persist(): Result<Unit>
}
