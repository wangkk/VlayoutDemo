package com.wk516.vlayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.wk516.vlayoutdemo.R;
import com.wk516.vlayoutdemo.bean.Theme;

import java.util.List;

/**
 * Created by Ke_Wang on 2019/3/15.
 */

public class HorizontalThemeAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Theme> mDatas;
    private LayoutHelper helper;

    public HorizontalThemeAdapter(Context context, List<Theme> mDatas, LayoutHelper helper) {
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
                .inflate(R.layout.item_horizontal_layout, parent, false);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ThemeViewHolder viewHolder = (ThemeViewHolder) holder;
        bindHorizontalList(viewHolder);
    }

    /**
     * 横向列表样式
     */
    private void bindHorizontalList(ThemeViewHolder holder) {
        //设置布局管理器(现行管理器ListView样式，支持横向、纵向)
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 2);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向
        holder.rvHorizontal.setLayoutManager(linearLayoutManager);

        //设置适配器
        NineThemeAdapter nineThemeAdapter = new NineThemeAdapter(context, mDatas);
        holder.rvHorizontal.setAdapter(nineThemeAdapter);
        nineThemeAdapter.setOnHorizontalListItemClickLitener(new NineThemeAdapter.OnHorizontalListItemClickLitener() {
            @Override
            public void onItemClick(Theme itemBean) {
                if (horizontalListCallback != null) {
                    horizontalListCallback.clickHorizontalItem(itemBean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class ThemeViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rvHorizontal;

        public ThemeViewHolder(View itemView) {
            super(itemView);
            rvHorizontal = itemView.findViewById(R.id.rv_horizontal);
        }
    }

    /**
     * 系列片点击回调
     */
    public interface HorizontalListCallback {
        void clickHorizontalItem(Theme itemBean);
    }

    /**
     * 系列片点击回调
     */
    private HorizontalListCallback horizontalListCallback;

    public void setHorizontalListCallback(HorizontalListCallback horizontalListCallback) {
        this.horizontalListCallback = horizontalListCallback;
    }
}
