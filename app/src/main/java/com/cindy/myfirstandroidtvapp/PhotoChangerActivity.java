package com.cindy.myfirstandroidtvapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class PhotoChangerActivity extends Activity {

    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photochanger);

        initData();
        initView();
    }

    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.test001);
        imagePath.add(R.drawable.test002);
        imagePath.add(R.drawable.test003);
        imagePath.add(R.drawable.test004);
        imagePath.add(R.drawable.test005);
        imageTitle.add("001");
        imageTitle.add("002");
        imageTitle.add("003");
        imageTitle.add("004");
        imageTitle.add("005");
    }

    private void initView() {
        mMyImageLoader = new MyImageLoader();
        mBanner = findViewById(R.id.PhotoChangerBanner);
        //设置样式，里面有很多种样式可以自己都看看效果
        //mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOut);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(4000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //設定指示器位置（當banner模式中有指示器時）
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //开始调用的方法，启动轮播图。
                .start();


    }

    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}
