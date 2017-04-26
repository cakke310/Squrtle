package com.squrtle.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.squrtle.R;
import com.squrtle.bean.Category;
import com.squrtle.common.Constant;
import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerCategoryComponent;
import com.squrtle.di.module.CategoryModule;
import com.squrtle.presenter.CategoryPresenter;
import com.squrtle.presenter.contract.CategoryContract;
import com.squrtle.ui.adapter.CategoryAdapter;
import com.squrtle.ui.activity.CategoryAppActivity;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Ivan on 16/9/26.
 */

public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract.CategoryView{


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private CategoryAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    private void initRecyclerView(){
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);

        recycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        recycleView.setAdapter(mAdapter);
        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("click ", "click");
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
