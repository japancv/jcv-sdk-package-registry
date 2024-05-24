# JCV Face SDK Sample

[English](README.md) | [日本語](README-Ja.md)

## Available JCV SDK libraries

The following libraries and plugin are available for the various JCV SDK functions.

| Gradle Plugin	                                    | Gradle Dependency                                                                                                            |
|---------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|
| SDK Config | [`plugin-build:jp.co.japancv.plugin-sdk-config:1.2.3`](https://github.com/japancv/jcv-sdk-package-registry/packages/2137930) |

| Libraries	                                                 | Gradle Dependency                                                                                                         |
|---------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| [Face Detection](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-detection/index.html)       | [`jp.co.japancv:sdk-face-detection:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138758)     |
| [Face Attributes](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-attributes/index.html)  | [`jp.co.japancv:sdk-face-attributes:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138754)    |
| [Face Quality](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-quality/index.html)    | [`jp.co.japancv:sdk-face-quality:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138764)       |
| [Face Liveness](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-liveness/index.html)   | [`jp.co.japancv:sdk-face-liveness:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138760)      |
| [Face Pose](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-pose/index.html) | [`jp.co.japancv:sdk-face-pose:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138762)          |
| [Gesture Detection](https://japancv.github.io/jcv-sdk-package-registry/jcv-gesture-detection/index.html) | [`jp.co.japancv:sdk-gesture-detection:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138766)  |

## 📦 Add JCV SDK to your project

### Prerequisites

- The minimum Android SDK Version: 34
- In Android project, you need to enable `buildConfig` feature,
```groovy
buildFeatures {
    buildConfig true
    ...
}
```

### Add SDK

1. Configuration file

Please download the SDK configuration file `jcv-sdk.json` and put on your project root.

2. Apply Gradle Plugin

##### If using `plugins` block

In the application level `build.gradle` file
```groovy
plugins {
  id 'jp.co.japancv.plugin-sdk-config' version '1.2.3'
}
```

##### [Legacy way] If using `buildscript` block

In the project level `build.gradle` file
```groovy
buildscript {
//  ...
  dependencies {
    classpath("plugin-build:jp.co.japancv.plugin-sdk-config:1.2.3")
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
    
    // オプション
    // implementation "jp.co.japancv:sdk-face-detection:3.0.0"
    // implementation "jp.co.japancv:sdk-face-attributes:3.0.0"
    // implementation "jp.co.japancv:sdk-face-liveness:3.0.0"
    // implementation "jp.co.japancv:sdk-face-pose:3.0.0"
    // implementation "jp.co.japancv:sdk-face-quality:3.0.0"
    // implementation "jp.co.japancv:sdk-gesture-detection:3.0.0"
}
```

#### Add Maven Repository

You will need to add a maven repository where can fetch for the plugin and SDK

```groovy
repositories {
  ...
  maven {
    url = uri("https://maven.pkg.github.com/japancv/jcv-sdk-package-registry")
    name = "JCV SDK Github"
    credentials {
      username = "YOUR GITHUB USERNAME"
      password = "YOUR GITHUB ACCESS TOKEN"
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

## 📄  Reference document

Please refer the following link:
https://japancv.github.io/jcv-sdk-package-registry/