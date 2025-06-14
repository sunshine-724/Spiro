// desktopApp/build.gradle.kts - 修正後
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

// ★★★ ここから追加 ★★★
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Java 21 を指定
    }
}
// ★★★ ここまで追加 ★★★

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
    // ★★★ ここから追加 ★★★
    jvmToolchain(21) // KotlinもJava 21 に合わせる
    // ★★★ ここまで追加 ★★★
}

compose.desktop {
    application {
        mainClass = "org.example.Main"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinMultiplatformComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}
