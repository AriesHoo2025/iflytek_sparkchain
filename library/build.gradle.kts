plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish` // 用于发布到 JitPack
}

android {
    namespace = "me.arieshoo.iflytek_sparkchain"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(files("libs/SparkChain.aar"))
}
// 关键部分：配置 Maven 发布，使 JitPack 能构建并发布该库
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // 从 Android Library 组件中构建发布内容
                from(components["release"])
                // 设置 groupId、artifactId、version —— 这是 Maven 坐标
                groupId = "me.arieshoo"        // 你可以改成自己的组织，如 com.yourname
                artifactId = "iflytek_sparkchain"       // 你的库名称，比如 mylibrary
                version = "1.0.0"              // 版本号，比如 1.0.0
            }
        }
    }
}