package com.squrtle.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by c_xuwei-010 on 2017/4/10.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
