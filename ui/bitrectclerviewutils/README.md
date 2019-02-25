### RecyclerView便携工具封装

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
implementation 'com.bitliker.android.ui:bitrectclerviewutils:v1.1.1'
```    
    
## 使用
- listener 封装RecycleView点击事件
- widget 封装ItemDecoration便携分割线
- baseadapter 封装RecyclerView通用适配器

### baseadapter使用
- 单布局
```
SingleAdapter<Object> mAdapter = new SingleAdapter<Object>(ct,layouId) {
            @Override
            protected void bindData(SuperViewHolder holder, Object item) {
                
            }
        };
```
- 多布局
```
MultiAdapter<Object> mAdapter = new MultiAdapter<Object>(ct) {
            @Override
            public int getLayoutId(Object item, int position) {
                //返回对应item的layout
                return 0;
            }
            @Override
            public void bindData(Context context, SuperViewHolder holder, Object item, int layoutId, int position) {

            }
        };        
```
    
