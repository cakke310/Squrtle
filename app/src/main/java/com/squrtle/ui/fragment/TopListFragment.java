package com.squrtle.ui.fragment;

import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerAppInfoComponent;
import com.squrtle.di.module.AppInfoModule;
import com.squrtle.presenter.AppInfoPresenter;
import com.squrtle.ui.adapter.AppInfoAdapter;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
public class TopListFragment extends BaseAppInfoFragment {


    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectTopListFragment(this);
    }

}
