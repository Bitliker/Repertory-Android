### 开发过程中积累和整理的一些工具类，BaseActivity和BaseFragment默认进度框和滑动退出等功能
#### 整理如下内容
- BaseApplication 封装Application，提供debug的判断等
- BaseFragment 封装V4包下Fragment，提供进度条和防内存重启错误问题
- SupportActionBarActivity  封装基本的带有Actionbar的Activity
- SupportToolsBarActivity  封装带有默认顶部Toolbar的Activity
- ViewPagerLazyFragment 懒加载Fragment 适合在ViewPager使用
- DisplayUtil 屏幕尺寸工具类
- KeyboardUtil 软键盘工具类
- LogUtil  log打印工具类，可以打印长log
- MacUtil  手机mac获取工具类
- NetworkUtils 判断网络类型工具类
- SpUtil 持久化保存工具类
- SystemUtil  系统信息获取工具类
- ToastUtils  toast工具类，后期添加修改


## 引入
- 在project的build.gradle中allprojects添加maven
```
allprojects {
        repositories {
            maven { url "https://raw.githubusercontent.com/BitlikerLibs/Libs-Android/master" }
            ...
        }
    }
```
- 在modular的build.gradle添加引用
```
implementation 'com.bitliker.android.core:bitutils:v1.1.0'
```    
