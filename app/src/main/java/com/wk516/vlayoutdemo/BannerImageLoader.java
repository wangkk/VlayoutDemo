package com.wk516.vlayoutdemo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by HaiyuKing
 * Used 自定义的Banner图片加载器
 */
public class BannerImageLoader extends ImageLoader {
	@Override
	public void displayImage(Context context, Object path, ImageView imageView) {

		Glide.with(context.getApplicationContext())
				.load(path)
				//设置等待时的图片
				//默认淡入淡出动画
				.transition(withCrossFade())
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				//设置图片加载的优先级
				.into(imageView);
	}

	//提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
	/*@Override
    public ImageView createImageView(Context context) {
         //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }*/
}
