package com.squrtle.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squrtle.AppApplication;
import com.squrtle.R;
import com.squrtle.di.component.AppComponent;
import com.squrtle.di.component.DaggerAppComponent;
import com.squrtle.di.component.DaggerRecommendComponent;
import com.squrtle.di.module.RecommendModule;
import com.squrtle.presenter.RecommentPresenter;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.ui.adapter.RecommendAppAdapter;
import com.squrtle.bean.AppInfo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends BaseFragment<RecommentPresenter> implements RecommendContract.View{
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private List<AppInfo> datas;
    private RecommendAppAdapter mAdapter;

//    @Inject
//    RecommendContract.Presenter mPresent;

    private ProgressDialog mProgressDialog;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void init() {
        mProgressDialog = new ProgressDialog(getActivity());
        mPresenter.requestDatas();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);

    }



    private void initRecyclerView(List<AppInfo> datas) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));

        mAdapter = new RecommendAppAdapter(datas,getActivity());
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void showNodata() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();

    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
