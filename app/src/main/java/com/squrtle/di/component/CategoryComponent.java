package com.squrtle.di.component;

import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.CategoryModule;
import com.squrtle.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);
}
