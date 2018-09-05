## 对于DialogFragment的封装实现提示框、输入框、列表选择框窗口调用
### 使用
#### Maven
    <dependency>
      <groupId>com.bitliker.android.ui</groupId>
      <artifactId>bitdialog</artifactId>
      <version>v1.0.0</version>
      <type>pom</type>
    </dependency>
#### Gradle
    implementation 'com.bitliker.android.ui:bitdialog:v1.0.0'
#### 提示框
      BitDialog.createPrompt(null)
                    .showNegativeAble(true)//是否显示确认按钮
                    .showPositiveAble(true)//是否需要显示取消按钮
                    .setCancelable(true)//是否允许取消
                    .setCanceledOnTouchOutside(true)//允许点击框外关闭窗体
                    .setContent(new WidgetParameter()
                            .setText("这个是内容")
                            .setTextColorResId(R.color.testColor)
                            .setTextSize(TypedValue.COMPLEX_UNIT_SP, 12)
                            .setWidgetListener(new PromptWidgetListener() {
                                @Override
                                public boolean onClick(View v) {
                                    return false;
                                }
                            })
                    )//内容详细设置
                    .setTitle("标题")//标题
                    .setAnimationsStyle(R.style.DialogBottomAnim)//显示动画
                    .setGravity(Gravity.BOTTOM)//显示方位
                    .setNegative("取消", new PromptWidgetListener() {
                        @Override
                        public boolean onClick(View v) {
                            return false;
                        }
                    })//取消按钮
                    .setPositive("确定", new PromptWidgetListener() {
                        @Override
                        public boolean onClick(View v) {
                            return false;
                        }
                    })///确定按钮
                    .show();
                    

#### 输入框
     BitDialog.createInput(null)
                    .showNegativeAble(true)//是否显示确认按钮
                    .showPositiveAble(true)//是否需要显示取消按钮
                    .setCancelable(true)//是否允许取消
                    .setCanceledOnTouchOutside(true)//允许点击框外关闭窗体
                    .setContent("这个是内容")//内容
                    .setTitle("标题")//标题
                    .setAnimationsStyle(R.style.DialogBottomAnim)//显示动画
                    .setGravity(Gravity.BOTTOM)//显示方位
                    .setNegative("清空", new PromptWidgetListener() {
                        @Override
                        public boolean onClick(View v) {
                            //返回 true表示拦截，不会自动关闭窗体
                            return false;
                        }
                    })//取消按钮
                    .setPositive("确定", new PromptWidgetListener() {
                        @Override
                        public boolean onClick(View v) {
                            
                            
                            return false;
                        }
                    })///确定按钮
                    .show();
                    
#### 选择框
      BitDialog.createList(null)
                    .showNegativeAble(true)//是否显示确认按钮
                    .showPositiveAble(true)//是否需要显示取消按钮
                    .setCancelable(true)//是否允许取消
                    .setCanceledOnTouchOutside(true)//允许点击框外关闭窗体
                    .setTitle("标题")//标题
                    .setAnimationsStyle(R.style.DialogBottomAnim)//显示动画
                    .setGravity(Gravity.BOTTOM)//显示方位
                    .setNegative("清空")//取消按钮
                    .setPositive("确定")///确定按钮
                    .show(ArrayList< BitDialogModel > models, OnMultiSelectListener onMultiSelectListener);
            //show 有两个重载函数   分别对应 单选和多选 