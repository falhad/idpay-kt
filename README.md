<p align="center">
  <a href="https://idpay.org/">
    <img src="https://idpay.org/nx/assets/media/logos/logo-letter-9.png" alt="IDPay Logo" width="96" height="125">
  </a>
  <h1 align="center">Welcome to IDPay-kt 👋</h1>
</p>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.1-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/falhad/idpay-kt/wiki" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="https://opensource.org/licenses/MIT" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>

  <a href="https://twitter.com/iarcxxi" target="_blank">
    <img alt="Twitter: iarcxxi" src="https://img.shields.io/twitter/follow/iarcxxi.svg?style=social" />
  </a>
</p>

> Connect to  <a href="https://idpay.ir" target="_blank">IDPay.ir</a> payment gateway from `Kotlin/Java` code base in easy way.

### 🏠 [Homepage](https://github.com/falhad/idpay-kt)

## Setup

we ⚡ Replace `$idpayKTVersion` with the latest version
in [Maven Repository](https://mvnrepository.com/artifact/dev.falhad/idpay)
or [Maven Central](https://search.maven.org/search?q=dev.falhad).

### Kotlin (build.gradle.kts)

1- Add `mavenCentral()` to your `repositories` section of `build.gradle.kts` if not already exists.

```kotlin
repositories {
    mavenCentral()
}
```

2- Add dependency to your project.

```kotlin
dependencies {
    implementation("dev.falhad:idpay:$idpayKTVersion")
}
```

### Groovy

1- Add `maven()` to your `repositories` section of `build.gradle` if not already exists.

```groovy
repositories {
    maven()
}
```

2- Add dependency to your project.

```groovy
dependencies {
    implementation 'dev.falhad:idpay:$idpayKTVersion'
}
```

## 🚀 Basic Usage

If you are not familiar with the transaction processing flow its good to
see [IDPay.ir documents](https://idpay.ir/web-service/v1.1/) first.

### Initialize IDPay-KT

```kotlin
val idPay = IDPay(apiKey = IDPAY_API_KEY, sandbox = true, logging = false)
```

### Step 1 - Generate Token

```kotlin
idPay
    .requestToken("your-order-id", 1000, "your-website-callback-api")
    .fold(onSuccess = { response ->
        println("hooray! transaction (${response.id} generated.")
        println("redirect user to ${response.link}")
    }, onFailure = { error ->
        when (error) {
            is IDPayException -> println("${error.msg} | ${error.code}")
            else -> println("something went wrong. ${error.message}")
        }
    })
```

You may want to save `id`, `orderId` and `amount` for
prevent [Double-spending](https://en.wikipedia.org/wiki/Double-spending) problem.


### Step 2 - Verify Payment

If you set autoVerify to `false` in step 1 you should verify the transaction within 10 minutes or transaction will be
reverted automatically.

#### Verify Payment

```kotlin
idPay.verify("id", "orderId")
    .fold({ response ->
        println("payment verified.\n$response")
    }, { error ->
        when (error) {
            is IDPayException -> println("${error.msg} | ${error.code}")
            else -> println("something went wrong. ${error.message}")
        }
    })
```

## Author

👤 **Farhad Navayazdan**

* Website: https://falhad.dev
* Twitter: [@iarcxxi](https://twitter.com/iarcxxi)
* Github: [@falhad](https://github.com/falhad)
* LinkedIn: [@farhadarcxx](https://linkedin.com/in/farhadarcxx)

## Show your support

Give a ⭐️ if this project helped you!

My Public Address to Receive `USDT (TRC20)` is `TQ43UdYtFAHQCNVciNCd9ndE4TQaMMzboX`

## 📝 License

Copyright © 2021 [Farhad Navayazdan](https://github.com/falhad).

This project is [MIT](https://opensource.org/licenses/MIT) licensed.

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_