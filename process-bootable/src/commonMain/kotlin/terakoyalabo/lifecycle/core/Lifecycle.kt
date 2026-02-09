package terakoyalabo.lifecycle.core

interface Lifecycle {
    fun onStarting()
    fun onStarted()
    fun onStopping()
    fun onStopped()
}
