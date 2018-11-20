# Idea 提交aar到github
## 一.准备远程空间
### 1.登陆github账号
### 2.创建一个组织
### 3.创建一个仓库Repository，开源协议尽量选择Apache-2.0
### 4.git 下来仓库代码到本地

## 二.项目中配置
### 1.在module下build.gradle中添加如下：
```
ext.versionCode = '3.11.0.6'             //版本号
ext.versionName = 'v' + versionCode      //版本名字
ext.localRepoPath = 'D:/WorkSpace/Android/libs-android' //本地依赖库绝对地址
ext.groupId = 'com.bitliker.android.controller' //组织地址
ext.artifactId = 'BitNetWork'              //开源库Id 最后形式为 'com.bitliker.controller:BitNetWork:v1.0.0'
ext.desction = '封装网络请求'            //备注

apply from: 'https://gitee.com/Bitliker/BaseConfig/raw/master/github.gradle'
```
    
### 2.刷新Gradle

### 3.点击右边栏Gradle->打开对应的module->Tasks->upload->双击uploadArchives

### 4.进入（一.3） 项目中，通过git上传代码到github

## 三.在项目中使用上传上去的开源库
### 1.在项目工程Gradle文件下添加
``` 
allprojects{
    repositories {
     maven { url "https://raw.githubusercontent.com/userName/project/master" }
     //userName:用户名（上传的aar库所属的github用户名）
     //project:项目名称（用于存放项目的仓库名）
    }
}
```



