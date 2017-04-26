package com.squrtle.di.component;

import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.AppInfoModule;
import com.squrtle.ui.fragment.CategoryAppFragment;
import com.squrtle.ui.fragment.GamesFragment;
import com.squrtle.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
@FragmentScope
@Component(modules= AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {
    void injectTopListFragment(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);
    void injectCagetoryAppFragment(CategoryAppFragment fragment);
}
