package com.squrtle.di.component;

import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.AppDetailModule;
import com.squrtle.ui.activity.AppDetailActivity;
import com.squrtle.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/27.
 */
@FragmentScope
@Component(modules = AppDetailModule.class, dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);
}
