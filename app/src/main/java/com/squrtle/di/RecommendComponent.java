package com.squrtle.di;

import com.squrtle.ui.fragment.RecommendFragment;

import dagger.Component;
import dagger.Module;

/**
 * Created by c_xuwei-010 on 2017/4/6.
 */
@Component(modules = RecommendModule.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
