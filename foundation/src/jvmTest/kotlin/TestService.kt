import terakoyalabo.foundation.ktor.KernelConfiguration
import terakoyalabo.foundation.ktor.KtorApplicationKernel
import terakoyalabotest.library.feature.TestApplicationAuthJwt
import terakoyalabotest.library.feature.TestRouteContentNegotiation
import kotlin.test.Test

class TestService : KtorApplicationKernel(
    configuration = KernelConfiguration()
) {
    @Test // ここ！関数（メソッド）に授ける
    fun launch() {
        // 1. 具象化したサービスを生成
        val service = object : KtorApplicationKernel(
            configuration = KernelConfiguration(port = 8080)
        ) {
            // 必要に応じてライフサイクルメソッドを調整
        }

        Builder(kernel = service)
            .applyApp(endurable = TestApplicationAuthJwt())
            .applyRoute(endurable = TestRouteContentNegotiation())
            .build()
            .run()

        println("サーバーは現在、8080ポートで生存しています。Ctrl+Cで停止してください。")

        // テストプロセスが死なないように、スレッドを眠らせ続ける
        // Thread.currentThread().join()
    }
}
