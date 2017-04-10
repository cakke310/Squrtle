package com.squrtle.di.component;

import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.RecommendModule;
import com.squrtle.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
