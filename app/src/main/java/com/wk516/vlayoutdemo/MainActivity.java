package com.wk516.vlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.wk516.vlayoutdemo.adapter.AdAdapter;
import com.wk516.vlayoutdemo.adapter.BanerAdapter;
import com.wk516.vlayoutdemo.adapter.GridAdapter;
import com.wk516.vlayoutdemo.adapter.HorizontalThemeAdapter;
import com.wk516.vlayoutdemo.adapter.SearchAdapter;
import com.wk516.vlayoutdemo.adapter.StickyTitleAdapter;
import com.wk516.vlayoutdemo.bean.Ad;
import com.wk516.vlayoutdemo.bean.BannerBean;
import com.wk516.vlayoutdemo.bean.GoodBean;
import com.wk516.vlayoutdemo.bean.ModelBean;
import com.wk516.vlayoutdemo.bean.Theme;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String image = "https://publish-pic-cpu.baidu.com/7d9efffd-4586-4361-8f43-91909f421597.jpeg";
    @BindView(R.id.rv_content)
    public RecyclerView rvContent;
    private List<BannerBean> bannerList = new ArrayList<>();
    private List<Theme> themList = new ArrayList<>();
    private List<GoodBean> goodBeans = new ArrayList<>();
    private DelegateAdapter adapter;
    private List<ModelBean> modelList = new ArrayList<>();
    private List<Ad> adList = new ArrayList<>();
    private int viewType;
    private RecyclerView.RecycledViewPool recycledViewPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initModel();
        initData();
        initRecyclerPool();
        initRecyclerView();
    }

    private void initModel() {
        modelList.add(new ModelBean("1", "搜索栏"));
        modelList.add(new ModelBean("2", "滚动图片栏"));
        modelList.add(new ModelBean("3", "菜单按钮"));
        modelList.add(new ModelBean("4", "广告魔方"));
        modelList.add(new ModelBean("5", "拼团板块"));
        modelList.add(new ModelBean("6", "秒杀板块"));
        modelList.add(new ModelBean("7", "热卖商品"));
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            BannerBean bannerBean = new BannerBean();
            bannerBean.setImgUrl(image);
            bannerList.add(bannerBean);
        }
        for (int i = 0; i < 15; i++) {
            Theme theme = new Theme();
            theme.setImgUrl(image);
            theme.setName("主题" + i);
            themList.add(theme);
        }
        for (int i = 0; i < 8; i++) {
            GoodBean good = new GoodBean();
            good.setImgUrl(image);
            good.setName("标题" + i);
            goodBeans.add(good);
        }
        for (int i = 0; i < 3; i++) {
            Ad ad = new Ad();
            ad.setImgUrl(image);
            adList.add(ad);
        }
    }

    private void initRecyclerView() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvContent.setLayoutManager(virtualLayoutManager);
        adapter = new DelegateAdapter(virtualLayoutManager, false);
        rvContent.setAdapter(adapter);
        setVLayoutAdapter();
    }

    private List<DelegateAdapter.Adapter> adapters;

    private void setVLayoutAdapter() {
        viewType = 0;
        if (adapters != null) {
            adapters.clear();
        } else {
            adapters = new LinkedList<>();
        }
        for (int i = 0; i < modelList.size(); i++) {
            String type = modelList.get(i).getType();
            switch (type) {
                case "1":
                    //搜索栏
                    recycledViewPool.setMaxRecycledViews(viewType++, 1);
                    SingleLayoutHelper searchHelper = new SingleLayoutHelper();
                    searchHelper.setMargin(0, 0, 0, 10);
                    SearchAdapter searchAdapter = new SearchAdapter(this, searchHelper);
                    adapters.add(searchAdapter);
                    break;
                case "2":
                    //轮播图
                    recycledViewPool.setMaxRecycledViews(viewType++, bannerList.size());
                    SingleLayoutHelper bannerHelper = new SingleLayoutHelper();
                    bannerHelper.setMargin(0, 0, 0, 10);
                    BanerAdapter banerAdapter = new BanerAdapter(this, bannerList, bannerHelper);
                    banerAdapter.setBannerCallback(new BanerAdapter.BannerCallback() {
                        @Override
                        public void clickBanner(BannerBean itemBean) {
                            Toast.makeText(MainActivity.this, "轮播图被点了", Toast.LENGTH_SHORT).show();
                        }
                    });
                    adapters.add(banerAdapter);
                    break;
                case "3":
                    //横向主题
                    recycledViewPool.setMaxRecycledViews(viewType++, themList.size());
                    SingleLayoutHelper horizontalListLayoutHelper = new SingleLayoutHelper();//不使用LinearLayoutHelper
                    horizontalListLayoutHelper.setMargin(0, 0, 0, 10);
                    HorizontalThemeAdapter horizontalListLayoutAdapter = new HorizontalThemeAdapter(this, themList, horizontalListLayoutHelper);
                    horizontalListLayoutAdapter.setHorizontalListCallback(new HorizontalThemeAdapter.HorizontalListCallback() {
                        @Override
                        public void clickHorizontalItem(Theme itemBean) {
                            Toast.makeText(MainActivity.this,
                                    itemBean.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });//设置自定义回调，用于点击事件监听
                    adapters.add(horizontalListLayoutAdapter);
                    break;
                case "4":
                    //广告魔方
                    recycledViewPool.setMaxRecycledViews(viewType++, 1);
                    OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(5);
                    // 在构造函数里传入显示的Item数
                    // 公共属性
                    onePlusNLayoutHelper.setItemCount(3);// 设置布局里Item个数
                    onePlusNLayoutHelper.setPadding(5, 5, 5, 5);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
                    onePlusNLayoutHelper.setMargin(5, 5, 5, 5);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
                    onePlusNLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

                    AdAdapter adAdapter = new AdAdapter(this, onePlusNLayoutHelper, adList);
                    adapters.add(adAdapter);
                    break;
                case "5":
                    //拼团
                    recycledViewPool.setMaxRecycledViews(viewType++, 1);
                    StickyLayoutHelper stickyLayoutHelper5 = new StickyLayoutHelper();
                    StickyTitleAdapter stickyTitleAdapter5 = new StickyTitleAdapter(this, stickyLayoutHelper5, modelList.get(i).getName());
                    adapters.add(stickyTitleAdapter5);

                    recycledViewPool.setMaxRecycledViews(viewType++, goodBeans.size());
                    LinearLayoutHelper linearHelper = new LinearLayoutHelper();//不使用LinearLayoutHelper
                    linearHelper.setMargin(0, 0, 0, 10);
                    GridAdapter gridThemeAdapter5 = new GridAdapter(this, goodBeans, linearHelper);
                    adapters.add(gridThemeAdapter5);
                    break;
                case "6":
                    //秒杀
                    recycledViewPool.setMaxRecycledViews(viewType++, 1);
                    StickyLayoutHelper stickyLayoutHelper6 = new StickyLayoutHelper();
                    StickyTitleAdapter stickyTitleAdapter6 = new StickyTitleAdapter(this, stickyLayoutHelper6, modelList.get(i).getName());
                    adapters.add(stickyTitleAdapter6);

                    recycledViewPool.setMaxRecycledViews(viewType++, goodBeans.size());
                    LinearLayoutHelper seckillHelper = new LinearLayoutHelper();//不使用LinearLayoutHelper
                    seckillHelper.setMargin(0, 0, 0, 10);
                    GridAdapter gridThemeAdapter6 = new GridAdapter(this, goodBeans, seckillHelper);
                    adapters.add(gridThemeAdapter6);
                    break;
                case "7":
                    // 商品列表
                    recycledViewPool.setMaxRecycledViews(viewType++, 1);
                    StickyLayoutHelper stickyLayoutHelper7 = new StickyLayoutHelper();
                    StickyTitleAdapter stickyTitleAdapter7 = new StickyTitleAdapter(this, stickyLayoutHelper7, modelList.get(i).getName());
                    adapters.add(stickyTitleAdapter7);

                    recycledViewPool.setMaxRecycledViews(viewType++, goodBeans.size());
                    GridLayoutHelper goodListHelper = new GridLayoutHelper(2);//不使用LinearLayoutHelper
                    goodListHelper.setAutoExpand(false);
                    goodListHelper.setMargin(0, 0, 0, 10);
                    GridAdapter gridThemeAdapter7 = new GridAdapter(this, goodBeans, goodListHelper);
                    adapters.add(gridThemeAdapter7);
                    break;
            }
        }
        adapter.setAdapters(adapters);
    }

    /**
     * 设置复用池
     */
    private void initRecyclerPool() {
        recycledViewPool = new RecyclerView.RecycledViewPool();
        rvContent.setRecycledViewPool(recycledViewPool);
    }
}
