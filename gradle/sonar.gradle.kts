// gradle/sonar.gradle.kts

// ルートプロジェクトへの設定
sonar {
    properties {
        property("sonar.projectKey", "kmupdate1_terakoyalabo_library_project")
        property("sonar.organization", "terakoya-labo")
        property("sonar.projectName", "Terakoyalabo Library Project")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token", System.getenv("SONAR_QUBE_CLOUD_TOKEN"))
        property("sonar.exclusions", "**/generated/**")
    }
}

// 全サブプロジェクトへの共通設定
subprojects {
    apply(plugin = "org.sonarqube")

    // 型安全のために直接 configure を叩く
    configure<org.sonarqube.gradle.SonarExtension> {
        properties {
            property("sonar.sources", "src/commonMain/kotlin")
        }
    }
}
