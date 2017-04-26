package com.squrtle.presenter.contract;

import com.squrtle.bean.BaseBean;
import com.squrtle.bean.Category;
import com.squrtle.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
public interface CategoryContract {
    public interface ICategoryModel{
        public Observable<BaseBean<List<Category>>> getCategories();
    }

    public interface CategoryView extends BaseView{
        public void showData(List<Category> categories);
    }
}
