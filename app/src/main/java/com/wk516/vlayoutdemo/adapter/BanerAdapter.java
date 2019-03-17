package com.wk516.vlayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.wk516.vlayoutdemo.BannerImageLoader;
import com.wk516.vlayoutdemo.R;
import com.wk516.vlayoutdemo.bean.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ke_Wang on 2019/3/15.
 */

public class BanerAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BannerBean> mDatas;
    private LayoutHelper helper;

    public BanerAdapter(Context context, List<BannerBean> mDatas, LayoutHelper helper) {
        this.context = context;
        this.mDatas = mDatas;
        this.helper = helper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BannerHolder recyclerViewHolder = (BannerHolder) holder;
        bindBanner(recyclerViewHolder);
    }

    private void bindBanner(BannerHolder holder) {
        //轮播图的常规设置
        holder.banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        holder.banner.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置只显示指示器
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        setBannerData(holder);//设置轮播图的数据
    }


    /**
     * 设置轮播图的数据
     */
    private void setBannerData(final BannerHolder holder) {
        List<String> images = new ArrayList<>();
        for (BannerBean itemBean : mDatas) {
            images.add(itemBean.getImgUrl());
        }
        holder.banner.setImages(images);
        holder.banner.isAutoPlay(true);
        //banner设置方法全部调用完毕时最后调用
        holder.banner.start();
        //设置viewpager的滑动监听
        holder.banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //设置点击事件，下标是从0开始
        holder.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (bannerCallback != null) {
                    bannerCallback.clickBanner(mDatas.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 正常条目的item的ViewHolder
     */
    private class BannerHolder extends RecyclerView.ViewHolder {

        public Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.home_banner);
        }
    }

    /**
     * 轮播图点击回调
     */
    public interface BannerCallback {
        void clickBanner(BannerBean itemBean);
    }

    private BannerCallback bannerCallback;

    public void setBannerCallback(BannerCallback bannerCallback) {
        this.bannerCallback = bannerCallback;
    }
}
