package com.squrtle.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squrtle.R;
import com.squrtle.bean.AppInfo;
import com.squrtle.common.imageloader.ImageLoader;

import java.util.List;

/**
 * Created by c_xuwei-010 on 2017/4/18.
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    public AppInfoAdapter() {
        super(R.layout.template_appinfo);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());


    }
}
