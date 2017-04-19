package com.squrtle.di.component;

import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.TopListModule;
import com.squrtle.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/19.
 */
@FragmentScope
@Component(modules= TopListModule.class,dependencies = AppComponent.class)
public interface TopListComponent {
    void inject(TopListFragment fragment);
}
