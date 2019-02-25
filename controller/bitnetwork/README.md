# 封装OkHttp，便携请求过程，因引入rxJava会和很多库出现冲突，现在移除rxJava，精简类库

## 引入
```
在project的build.gradle中allprojects添加maven
    allprojects {
        repositories {
            maven { url "https://raw.githubusercontent.com/BitlikerLibs/Libs-Android/master" }
            ...
        }
    }
```
- 在modular的build.gradle添加引用 
```
implementation 'com.bitliker.android.controller:bitnetwork:v3.12.0.2'
```    
## 使用
     ...
