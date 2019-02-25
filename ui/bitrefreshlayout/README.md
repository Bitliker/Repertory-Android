### 下拉刷新控件

## 引入
- 在project的build.gradle中allprojects添加maven
    allprojects {
        repositories {
            maven { url "https://raw.githubusercontent.com/BitlikerLibs/Libs-Android/master" }
            ...
        }
    }

- 在modular的build.gradle添加引用
    implementation 'com.bitliker.android.ui:bitrefreshlayout:v1.0.0'
    
    
## 使用
- 在xml中
```
<com.bitliker.ui.bitrefreshlayout.simlpe.SimpleRefreshLayout
    android:id="@+id/mSimpleRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/mRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</com.bitliker.ui.bitrefreshlayout.simlpe.SimpleRefreshLayout>
```

- 在代码中
```
        BaseRefreshLayout mRefreshLayout=findViewById(R.id.mSimpleRefreshLayout);
        //允许上拉加载
        mRefreshLayout.setEnabledPullUp(true);
        //是否允许下拉刷新
        mRefreshLayout.setEnablePullDown(false);
        mRefreshLayout.setOnRefreshListener(new BaseRefreshLayout.onRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新回调
            }

            @Override
            public void onLoadMore() {
                //加载回调
            }
        });
```

## 自定义
- 继承BaseRefreshLayout，实现getHeader和getFooter方法
- 继承BaseRefreshView，实现startAnim、stopAnim、upStatus方法，设置刷新开始、刷新结束、变更状态的显示