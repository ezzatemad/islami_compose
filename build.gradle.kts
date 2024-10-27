// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false

    id("com.google.dagger.hilt.android") version "2.50" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("app.cash.sqldelight:gradle-plugin:2.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
//        classpath("org.jetbrains.kotlin:kotlin-serialization:2.0.10")
    }
}



