package com.squrtle.presenter;

import com.squrtle.bean.Category;
import com.squrtle.common.rx.RxHttpResponseCompat;
import com.squrtle.common.rx.subscribe.ErrorHandlerSubscriber;
import com.squrtle.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by c_xuwei-010 on 2017/4/26.
 */
public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel,CategoryContract.CategoryView> {

    @Inject
    public CategoryPresenter(CategoryContract.ICategoryModel mModel, CategoryContract.CategoryView mView) {
        super(mModel, mView);
    }

    public void getAllCategory(){
        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<Category>>(mContext) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }
}
