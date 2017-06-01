# GlowDecorator
<a href='https://bintray.com/nickunuchek/maven/GlowDecorator?source=watch' alt='Get automatic notifications about new "GlowDecorator" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

<a href='https://bintray.com/nickunuchek/maven/GlowDecorator/1.0/link'><img src='https://api.bintray.com/packages/nickunuchek/maven/GlowDecorator/images/download.svg?version=1.0'></a>

<img src='https://i.stack.imgur.com/aFieS.jpg'>
<img src='https://github.com/Kolyall/GlowDecorator/blob/master/github/screenshot23.png' width="108" height="192">

```
dependencies {
      compile 'com.github.kolyall:glow-decorator:1.0'
}
```
Usage:
```
   @BindColor(R.color.grayLightColor) int mShadowColor;
   @BindView(R.id.frame) View mFrameView;
   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity)
         GlowDecorator.get(getContext())
                .glowRadius(16)//by default is 16
                .margin(24)//by default is 24
                .animationDuration(300)//by default is 300
                .imageRes(R.drawable.ic_laucher)//by default is applied from xml res of mFrameView  android:background=""
                .glowColor(mShadowColor)//by default is Color.WHITE
                .applyTo(mFrameView);
   }
```
main_activity.xml :
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
               >

        <LinearLayout
            android:id="@+id/frame"
            android:layout_width="match_parent"
            android:layout_height="64dp"
            android:orientation="vertical"
            android:layout_centerInParent="true"
            android:layout_marginBottom="30dp"
            android:layout_marginEnd="64dp"
            android:layout_marginLeft="64dp"
            android:layout_marginRight="64dp"
            android:layout_marginStart="64dp"
            android:layout_marginTop="60dp"
            android:background="@drawable/dialog_frame_background"
            />

</RelativeLayout>
```
dialog_frame_background.xml
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
    <corners android:radius="15dp"/>

    <solid android:color="#000000"/>
</shape>
```
