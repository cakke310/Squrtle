package com.squrtle.data;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.Category;
import com.squrtle.data.http.ApiService;
import com.squrtle.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
public class CategoryModel implements CategoryContract.ICategoryModel {

    private ApiService apiService;

    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return apiService.getCategories();
    }
}
