package terakoyalabo.lifecycle.domain

interface Retryable {
    fun onConnectionLost()
}
