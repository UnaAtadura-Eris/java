# java
setContentView(R.layout.activity_main);设置Activity的布局。通过R.layout.activity_main，我们指定了布局文件的资源ID，这个布局文件将在Activity中显示。


<EditText
        android:id="@+id/startTimeEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="开始时间"
        android:inputType="datetime"/>
<EditText
startTimeEditText = findViewById(R.id.startTimeEditText);// 获取当前时间
在布局文件里设置一个输入框，然后在java文件里通过ID获取到输入值
          
          
        startTimeEditText = findViewById(R.id.startTimeEditText);// 获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        // 将当前时间设置为EditText的默认文本
        startTimeEditText.setText(currentTime);
          
          android:layout_gravity="clip_vertical"是一个布局属性，用于指定视图在父容器中的垂直对齐方式。
          android:layout_margin="10dp"组件与周围的边距
          
          
          
          
          calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接受一个View.OnClickListener对象作为参数，并在按钮被点击时触发相应的操作。
                }
        });
          
          
          
          Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show(); 是一个用于在应用中显示短时期消息的方法。
MainActivity.this 表示当前的活动（Activity）上下文，用于指定Toast要显示在哪个Activity上。
result 是要显示的消息内容，即计算得到的时间间隔结果。
Toast.LENGTH_SHORT 是Toast的持续时间，表示短时期显示。你也可以使用Toast.LENGTH_LONG来指定长时期显示。
show() 方法用于显示Toast消息。
          
          
          要设置 Android Studio 中应用程序的图标、启动图像和背景图像，可以按照以下步骤进行操作：

图标设置：

在 Android Studio 的项目视图中，导航到 "res" 文件夹。
找到 "mipmap" 文件夹，在其中添加应用程序的图标文件。通常，应该在不同的 mipmap 文件夹中添加不同尺寸的图标文件以适配不同的设备。
然后，打开 "AndroidManifest.xml" 文件，在 <application> 标签中添加或更新 android:icon 属性，指定应用程序的图标资源。
在资源名称中使用 @mipmap/图标文件名 的格式引用图标文件，例如：android:icon="@mipmap/app_icon"。
启动图像设置：

在 Android Studio 的项目视图中，导航到 "res" 文件夹。
找到 "drawable" 文件夹，在其中添加应用程序的启动图像文件。
在 AndroidManifest.xml 文件中，打开 <activity> 标签，并为该活动指定一个主题。
在 android:theme 属性中，指定一个主题，例如：android:theme="@style/AppTheme"。
打开 res/values/styles.xml 文件，并在其中定义一个名为 "AppTheme" 的主题。
在主题中使用 windowBackground 属性，指定启动图像的资源文件。例如：
xml
Copy code
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="android:windowBackground">@drawable/launch_image</item>
</style>
背景图像设置：

在 Android Studio 的项目视图中，导航到 "res" 文件夹。
找到 "drawable" 文件夹，在其中添加应用程序的背景图像文件。
在布局文件中，找到要设置背景图像的视图元素，例如 <LinearLayout> 或 <RelativeLayout>。
使用 android:background 属性，指定背景图像的资源文件。例如：android:background="@drawable/background_image"。
          
          
          
          
          
          
          
