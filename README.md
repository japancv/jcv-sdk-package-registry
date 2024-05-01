# JCV Face SDK Sample

## 📦 Add JCV SDK to your project

### Prerequisites


### Add SDK

1. Configuration file

Please download the SDK configuration file `jcv-sdk.json` and put on your project root.

2. Apply Gradle Plugin

##### If using `plugins` block

In the application level `build.gradle` file
```groovy
plugins {
  id 'jp.co.japancv.plugin-sdk-config' version '1.2.1'
}
```

##### [Legacy way] If using `buildscript` block

In the project level `build.gradle` file
```groovy
buildscript {
//  ...
  dependencies {
    classpath("plugin-build:jp.co.japancv.plugin-sdk-config:1.2.1")
  }
}
```

In the application level `build.gradle` file
```groovy
plugins {
  id 'jp.co.japancv.plugin-sdk-config'
}
```


3. Install JCV Face SDK by adding the following dependency to your `build.gradle` file:

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "jp.co.japancv:sdk-face-detection:3.0.0"
}
```

#### Add Maven Repository

You will need to add a maven repository where can fetch for the plugin and SDK

```groovy
repositories {
  ...
  maven {
    url = uri("https://gitlab.com/api/v4/projects/48794739/packages/maven")
    name = "JCV SDK Gitlab"
    credentials {
      username = "YOUR GITLAB USERNAME"
      password = "YOUR GITLAB ACCESS TOKEN"
    }
    authentication {
      create("basic", BasicAuthentication::class)
    }
  }
}
```

Suggest to add in `settings.gradle` file for both `pluginManagement` and `dependencyResolutionManagement`

## ⚡️ Getting Started

```kotlin

/**
 * How to use JCV SDK
 */
scope.launch {
    // 1. Get the Face Detection instance
    val fd = FaceDetection.getInstance()
    // 2. Load your image as [android.graphics.Bitmap]
    val bitmap = BitmapFactory.decodeStream(assets.open("vic.jpg"))
    // 3. Initiate ImageAgent
    val imageAgent = bitmap.toImageAgent()
    // 4. Predict the face
    val info = fd.predict(imageAgent)
    // 5. Result
    info.forEach {
        Log.i("Sample", "${it.trackId}")
    }
}
```