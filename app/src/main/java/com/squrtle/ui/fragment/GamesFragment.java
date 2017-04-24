package com.squrtle.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squrtle.R;
import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerAppInfoComponent;
import com.squrtle.di.module.AppInfoModule;
import com.squrtle.presenter.AppInfoPresenter;
import com.squrtle.ui.adapter.AppInfoAdapter;


/**
 * Created by Ivan on 16/9/26.
 */

public class GamesFragment extends BaseAppInfoFragment {


    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(true).build();

    }



    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectGamesFragment(this);
    }
}
