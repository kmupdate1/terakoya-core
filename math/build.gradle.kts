plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.multiplatform }
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
        }
        commonTest.dependencies {  }
        jvmTest.dependencies {
            implementation(libs.lemonappdev.konsist)
            implementation(libs.junit.jupiter)
            runtimeOnly("org.junit.platform:junit-platform-launcher")
        }
    }

    jvmToolchain(21)
}

// build.gradle.kts のトップレベル（または一番下）に追記
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}
