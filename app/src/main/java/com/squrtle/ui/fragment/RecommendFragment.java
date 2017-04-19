package com.squrtle.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squrtle.R;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.IndexBean;
import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerRecommendComponent;
import com.squrtle.di.module.RecommendModule;
import com.squrtle.presenter.RecommentPresenter;
import com.squrtle.presenter.contract.AppInfoContract;
import com.squrtle.ui.adapter.IndexMultiAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends BaseFragment<RecommentPresenter> implements AppInfoContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private List<AppInfo> datas;
    private IndexMultiAdapter mAdapter;

//    @Inject
//    AppInfoContract.Presenter mPresent;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.requestPermission();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);

    }


    private void initRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());



    }





    @Override
    public void showResult(IndexBean indexBean) {
        mAdapter = new IndexMultiAdapter(getActivity());
        mAdapter.setData(indexBean);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestDatas();
    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(getActivity(),"用户拒绝授权",Toast.LENGTH_SHORT).show();

    }



}
