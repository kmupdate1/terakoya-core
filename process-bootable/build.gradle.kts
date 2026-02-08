plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.multiplatform }
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()

    sourceSets {
        commonMain.dependencies {

        }
        commonTest.dependencies {

        }
        jvmMain.dependencies {

        }
        jvmTest.dependencies {

        }
    }
}
