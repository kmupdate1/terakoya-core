package terakoyalabo.lifecycle.ktor

import io.ktor.server.application.*
import terakoyalabo.core.domain.identity.model.HeartBeat
import terakoyalabo.core.domain.identity.model.Identity
import terakoyalabo.core.domain.logic.sl
import terakoyalabo.lifecycle.node.Node
import terakoyalabo.lifecycle.node.ServiceNode
import terakoyalabo.lifecycle.core.StatusPublishable

abstract class KtorHttpNode : ServiceNode {
    private var node: Node = Node()

    fun bind(application: Application) {
        application.monitor.apply {
            subscribe(ApplicationStarting) {
                onEndue().onSuccess {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.ALIVE,
                        timestamp = HeartBeat.now(),
                        message = "Node endued successfully",
                    )
                }.onFailure {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.ALIVE,
                        timestamp = HeartBeat.now(),
                        message = "Node endue failed: ${it.message}",
                    )

                    throw it
                }

                onVerify().onSuccess {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.ALIVE,
                        timestamp = HeartBeat.now(),
                        message = "Node launched successfully",
                    )
                }.onFailure {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.DEAD,
                        timestamp = HeartBeat.now(),
                        message = "Node launched failed: ${it.message}",
                    )

                    throw it
                }
            }
            subscribe(ApplicationStopping) {
                onRetire(10_000.sl).onSuccess {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.RETIRING,
                        timestamp = HeartBeat.now(),
                        message = "Node stopped successfully",
                    )
                }
            }
            subscribe(ApplicationStopped) {
                onRelease().onSuccess {
                    node = node.copy(
                        statusId = Identity.gen(),
                        status = StatusPublishable.NodeStatus.DEAD,
                        timestamp = HeartBeat.now(),
                        message = "Node killed successfully",
                    )
                }
            }
        }
    }

    override fun publish(): StatusPublishable.NodeStatus = node.status
}
