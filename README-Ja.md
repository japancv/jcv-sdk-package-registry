# JCV Face SDK サンプル

[English](README.md) | [日本語](README-Ja.md)

## 利用可能なJCV SDKライブラリ

JCV SDK の各種機能については、以下のライブラリやプラグインが利用可能です。

| Gradle プラグイン	                                    | Gradle 依存                                                                                                                    |
|---------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|
| SDK Config | [`plugin-build:jp.co.japancv.plugin-sdk-config:1.2.3`](https://github.com/japancv/jcv-sdk-package-registry/packages/2137930) |

| ライブラリ	                                                 | Gradle 依存                                                                                                         |
|---------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| [Face Detection](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-detection/index.html)       | [`jp.co.japancv:sdk-face-detection:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138758)     |
| [Face Attributes](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-attributes/index.html)  | [`jp.co.japancv:sdk-face-attributes:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138754)    |
| [Face Quality](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-quality/index.html)    | [`jp.co.japancv:sdk-face-quality:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138764)       |
| [Face Liveness](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-liveness/index.html)   | [`jp.co.japancv:sdk-face-liveness:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138760)      |
| [Face Pose](https://japancv.github.io/jcv-sdk-package-registry/jcv-face-pose/index.html) | [`jp.co.japancv:sdk-face-pose:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138762)          |
| [Gesture Detection](https://japancv.github.io/jcv-sdk-package-registry/jcv-gesture-detection/index.html) | [`jp.co.japancv:sdk-gesture-detection:3.0.0`](https://github.com/japancv/jcv-sdk-package-registry/packages/2138766)  |


## 📦 JCV SDKをプロジェクトに追加方法

### 必要な事項

- Android SDKの最小バージョン: 34

### SDKを追加

1. 設定ファイル

JCV CloudでSDK設定ファイル `jcv-sdk.json` をダウンロードして、プロジェクトルートに置いてください。

2. Gradleプラグインを適用する

##### `plugins` blockを使用する場合

アプリケーションレベル `build.gradle` ファイル
```groovy
plugins {
  id 'jp.co.japancv.plugin-sdk-config' version '1.2.3'
}
```

##### [古い方法] `buildscript` blockを使用する場合

プロジェクトレベル `build.gradle` ファイル
```groovy
buildscript {
//  ...
  dependencies {
    classpath("plugin-build:jp.co.japancv.plugin-sdk-config:1.2.3")
  }
}
```

アプリケーションレベル `build.gradle` ファイル
```groovy
plugins {
  id 'jp.co.japancv.plugin-sdk-config'
}
```


3. 次の依存関係を `build.gradle` ファイルに追加して、JCV Face SDK をインストールします

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

#### Maven Repositoryを追加

You will need to add a maven repository where can fetch for the plugin and SDK

```groovy
repositories {
  ...
  maven {
    url = uri("https://maven.pkg.github.com/japancv/jcv-sdk-package-registry")
    name = "JCV SDK Github"
  }
}
```

`settings.gradle`ファイルに `pluginManagement` と `dependencyResolutionManagement` の両方を追加することにおすすめです。

## ⚡️ はじめに

```kotlin

/**
 * 基本のJCV SDKの使い方
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

## 📄  API参考ドキュメント

以下のリンクをご参照ください：
https://japancv.github.io/jcv-sdk-package-registry/
(現時点英語資料のみです)