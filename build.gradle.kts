// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.android.library") version "7.2.1" apply false
    id("androidx.benchmark") version "1.1.1" apply false
    id("com.google.devtools.ksp").version("1.8.20-1.0.11")
    id("com.kanyun.kudos") version "1.8.20-1.0.2-SNAPSHOT" apply false
}