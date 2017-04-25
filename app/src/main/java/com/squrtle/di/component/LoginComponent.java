package com.squrtle.di.component;

import com.squrtle.data.LoginModel;
import com.squrtle.di.FragmentScope;
import com.squrtle.di.module.LoginModule;
import com.squrtle.ui.atcitivy.LoginActivity;

import dagger.Component;

/**
 * Created by c_xuwei-010 on 2017/4/25.
 */
@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);

}
