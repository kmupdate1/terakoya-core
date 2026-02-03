subprojects {
    // 1. 各州が「外交官(maven-publish)」を雇っている場合のみ、中央政府が介入する
    plugins.withId("maven-publish") {
        configure<PublishingExtension> {
            publications {
                // ここに各州ごとの成果物定義を書く
                // (現状の core や math にある publishing ブロックをここに統合できます)

                // 2. 「maven」という名前の成果物を自動生成
                withType<MavenPublication>().configureEach {
                    if (artifactId == project.name + "-kotlinMultiplatform") artifactId = project.name
                }
            }

            // 3. 輸出先（Nexus）の判定と設定
            repositories {
                maven {
                    val repoType = if (isRelease) "releases" else "snapshots"
                    val address = if (isAtLabo) rootProject.findProperty("nexus.ip.labonet")?.toString() ?: "192.168.11.6"
                    else rootProject.findProperty("nexus.ip.vpn")?.toString() ?: "100.98.144.29"

                    val domain = "http://$address:8081"
                    val uri = uri("$domain/repository/terakoyalabo-library-$repoType")

                    url = uri

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
