package terakoyalabo.lifecycle.domain

interface StatusPublishable {
    fun publish(): NodeStatus

    enum class NodeStatus {
        STARTING, ALIVE, RETIRING, DEAD;
    }
}
