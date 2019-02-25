# 封装fastjson使用，隔离fastjson Api，异常处理优化
## 整理如下内容
- JSONUtil 封装基本操作工具，同时添加异常处理


## 引入
- 在project的build.gradle中allprojects添加maven
    allprojects {
        repositories {
            maven { url "https://raw.githubusercontent.com/BitlikerLibs/Libs-Android/master" }
            ...
        }
    }

- 在modular的build.gradle添加引用
    implementation 'com.bitliker.android.controller:bitjson:v1.1.7.6'
    
## 使用
    JSONObject object=JSONUtil.parseObject(messgae);
    boolean isOk=JSONUtil.getBoolean(object,"isOk");
    ...