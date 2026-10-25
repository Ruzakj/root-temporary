plugins { id("com.android.application") }

android {
    namespace = "com.ruzak.roottemporary"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.ruzak.roottemporary"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-diagnostics"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
