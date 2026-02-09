import terakoyalabo.core.domain.primitive.model.ScalarL
import terakoyalabo.lifecycle.ktor.KtorHttpNode

class NodeTest : KtorHttpNode() {
    /*
    private val node by lazy {
        createNode { TerakoyaSettings() }.also { it.boot() }
    }

    fun stop() {
        node.shutdown().fold(
            onSuccess = { exitProcess(0) },
            onFailure = { "Node is sleeping." },
        )
    }
     */

    override fun onEndue(): Result<Unit> {
        println("ここでFeatureを適合")
        TODO("Not yet implemented")
    }

    override fun onVerify(): Result<Unit> {
        println("ここでリソースの状態をチェック開始")
        TODO("Not yet implemented")
    }

    override fun onRetire(timeoutMillis: ScalarL): Result<Unit> {
        println("ここでリソース解放の準備開始")
        TODO("Not yet implemented")
    }

    override fun onRelease(): Result<Unit> {
        println("ここでリソース解放")
        TODO("Not yet implemented")
    }
}
