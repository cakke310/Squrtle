package com.squrtle.di.module;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.Category;
import com.squrtle.data.CategoryModel;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.CategoryContract;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
@Module
public class CategoryModule {
    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView mView) {
        this.mView = mView;
    }

    @Provides
    public CategoryContract.CategoryView provideView(){
        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel provideModel(ApiService apiService){
        return new CategoryModel(apiService);
    }
}
