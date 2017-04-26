package com.squrtle.ui.fragment;

import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerAppInfoComponent;
import com.squrtle.di.module.AppInfoModule;
import com.squrtle.ui.adapter.AppInfoAdapter;

/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
public class CategoryAppFragment extends BaseAppInfoFragment {

    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 2;


    private int categoryId;
    private int fragmentType;

    public CategoryAppFragment(int categoryId,int fragmentType){
        this.categoryId = categoryId;
        this.fragmentType = fragmentType;
    }

    public static CategoryAppFragment newInstance(int categoryId, int fragmentType){
        return new CategoryAppFragment(categoryId,fragmentType);
    }

    @Override
    public void init() {
        mPresenter.requestCategoryApps(categoryId,page,fragmentType);
        initRecyclerView();
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectCagetoryAppFragment(this);
    }
}
