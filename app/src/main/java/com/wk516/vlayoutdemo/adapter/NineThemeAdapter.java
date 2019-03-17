package com.wk516.vlayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.wk516.vlayoutdemo.R;
import com.wk516.vlayoutdemo.bean.BannerBean;
import com.wk516.vlayoutdemo.bean.Theme;

import java.util.List;

/**
 * Created by Ke_Wang on 2019/3/15.
 */

public class NineThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Theme> mDatas;

    public NineThemeAdapter(Context context, List<Theme> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nine_theme, parent, false);
        return new ThemeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ThemeItemViewHolder viewHolder = (ThemeItemViewHolder) holder;
        Glide.with(context).load(mDatas.get(position).getImgUrl()).into(viewHolder.imgTheme);
        viewHolder.txtTheme.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class ThemeItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgTheme;
        public TextView txtTheme;

        public ThemeItemViewHolder(View itemView) {
            super(itemView);
            imgTheme = itemView.findViewById(R.id.img_theme);
            txtTheme = itemView.findViewById(R.id.txt_theme);
        }
    }

    /*=====================添加OnItemClickListener回调================================*/
    public interface OnHorizontalListItemClickLitener
    {
        /**列表项的点击事件*/
        void onItemClick(Theme itemBean);
    }

    private OnHorizontalListItemClickLitener mOnItemClickLitener;

    public void setOnHorizontalListItemClickLitener(OnHorizontalListItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
