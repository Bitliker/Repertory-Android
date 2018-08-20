# idea提交代码到bintray步骤
## 一.准备远程空间
### 1.登录[bintray](https://bintray.com/)注册一个帐号，并获取Api Key
### 2.创建一个组织
### 3.创建一个仓库Repository，类型选择Maven，开源协议尽量选择Apache-2.0

## 二.项目中配置
### 1.在project下build.gradle中添加如下：
     dependencies{}中加入
     classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:latest.integration' （1.5）
     classpath "com.github.dcendents:android-maven-gradle-plugin:latest.integration"（1.7.3）

### 2.在project下local.properties文件中添加用户名和appkey
	bintray.apikey=***********************
	bintray.user=name

### 3.在需要上传的module中的build.gradle最后添加如下代码：
    //TODO 按照自己bintray账号的要求填写以下内容
	ext.repertoyAllName = 'com.gxut.core'    //最终引用形式，如compile 'com.leon.lfilepicker:1.0.0'，其中lfilepicker在后面配置
    ext.versionCode = '1.0.4'                //发布版本号
    ext.versionName = 'v' + versionCode      //发布版本名字
    ext.repertoyName = 'core'                //仓库名称
    ext.projectName = 'httpclient'              //项目名称
    ext.organizationName = 'gxut'            //组织名称
    ext.desction = '对okhttp的封装'            //备注
    //添加依赖
    apply from:"https://gitee.com/Bitliker/BaseConfig/raw/master/bintray.gradle"                                                                                               
  

### 4.在idea 的Terminal里面窗口中输入
	gradlew install
	gradlew bintrayUpload

### 5.等待上传结束就可以了

### 6.常见错误处理
#### 6.1.'gradlew' 不是xxx的命令
> 在我的电脑=》属性=》高级系统设置=》添加GRADLE_HOME，指定本地的Gradle的版本，需和项目指定的版本一致，方法和设置JAVA_HOME方式一样





