package com.squrtle.ui.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.squrtle.R;
import com.squrtle.bean.Banner;
import com.squrtle.bean.IndexBean;
import com.squrtle.common.imageloader.ImageLoader;
import com.squrtle.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by c_xuwei-010 on 2017/4/17.
 */
public class IndexMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_ICON = 2;
    public static final int TYPE_APPS = 3;
    public static final int TYPE_GAMES = 4;

     private Context mContext;
    private final LayoutInflater layoutInflater;



    private IndexBean indexBean;

    public IndexMultiAdapter(Context mContext) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    public void setData(IndexBean indexBean) {
        this.indexBean = indexBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(layoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new NavIconViewHolder(layoutInflater.inflate(R.layout.template_nav_icon, parent, false));
        }
        else if(viewType == TYPE_APPS){
            return new AppViewHolder(layoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_APPS);
        }
        else if(viewType == TYPE_GAMES){
            return new AppViewHolder(layoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_GAMES);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<Banner> banners = indexBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.banner.setViewUrls(urls);
            bannerViewHolder.banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        }
        else if(position == 1){
            NavIconViewHolder navIconViewHolder = (NavIconViewHolder) holder;
            navIconViewHolder.layoutHotApp.setOnClickListener(this);
            navIconViewHolder.layoutHotGame.setOnClickListener(this);
            navIconViewHolder.layoutHotSubject.setOnClickListener(this);
        }
        else {
            AppViewHolder appViewHolder = (AppViewHolder) holder;
            AppInfoAdapter appInfoAdapter = new AppInfoAdapter();
            if(appViewHolder.type == TYPE_APPS){
                appViewHolder.mText.setText("热门应用");
                appInfoAdapter.addData(indexBean.getRecommendApps());
            }
            else {
                appViewHolder.mText.setText("热门游戏");
                appInfoAdapter.addData(indexBean.getRecommendGames());
            }
            appViewHolder.mRecyclerView.setAdapter(appInfoAdapter);
            appViewHolder.mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            banner.setImageLoader(new ImgLoader());
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_hot_app)
        LinearLayout layoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout layoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout layoutHotSubject;

        public NavIconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        int type;

        public AppViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.type = type;
            initRecyclerView();
        }

        private void initRecyclerView(){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext){
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            });

            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(itemDecoration);
        }

    }



    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }
    }
}
