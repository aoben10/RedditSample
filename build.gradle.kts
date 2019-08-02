plugins {
    id("com.github.ben-manes.versions") version Versions.benManes
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    tasks.whenTaskAdded {
        if (name == "assembleDebug") {
            dependsOn("dependencyUpdates")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
