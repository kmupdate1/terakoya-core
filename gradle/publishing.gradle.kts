subprojects {
    // maven-publish プラグインが適用されているサブプロジェクトにのみ、この設定を注入する
    plugins.withId("maven-publish") {
        // 型を明示的に指定して configure を呼ぶ
        configure<org.gradle.api.publish.PublishingExtension> {
            publications {
                // KMP特有の artifactId 補正
                withType<org.gradle.api.publish.maven.MavenPublication>().configureEach {
                    if (artifactId == "${project.name}-kotlinMultiplatform") artifactId = project.name
                }
            }

            repositories {
                maven {
                    // ルートプロジェクトで定義した変数を参照
                    val isRelease = rootProject.extra["isRelease"] as Boolean
                    val isAtLabo = rootProject.extra["isAtLabo"] as Boolean

                    val repoType = if (isRelease) "releases" else "snapshots"

                    val address = if (isAtLabo) rootProject.findProperty("nexus.ip.labonet")?.toString() ?: "192.168.11.6"
                    else rootProject.findProperty("nexus.ip.vpn")?.toString() ?: "100.98.144.29"

                    url = uri("http://$address:8081/repository/terakoyalabo-library-$repoType")
                    isAllowInsecureProtocol = true

                    credentials {
                        username = System.getenv("NEXUS_USERNAME")
                        password = System.getenv("NEXUS_PASSWORD")
                    }
                }
            }
        }
    }
}
