package com.wk516.vlayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.wk516.vlayoutdemo.R;
import com.wk516.vlayoutdemo.bean.Ad;

import java.util.List;

/**
 * Created by Ke_Wang on 2019/3/15.
 */

public class AdAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutHelper helper;
    private List<Ad> mDatas;

    public AdAdapter(Context context, LayoutHelper helper, List<Ad> mDatas) {
        this.context = context;
        this.helper = helper;
        this.mDatas = mDatas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ad, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdViewHolder adViewHolder = (AdViewHolder) holder;
        Glide.with(context).load(mDatas.get(position).getImgUrl()).into(adViewHolder.imgAd);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAd;

        public AdViewHolder(View itemView) {
            super(itemView);

            imgAd = itemView.findViewById(R.id.img_ad);
        }
    }
}
