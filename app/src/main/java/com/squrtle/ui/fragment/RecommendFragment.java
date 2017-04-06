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

import com.squrtle.R;
import com.squrtle.di.DaggerRecommendComponent;
import com.squrtle.di.RecommendModule;
import com.squrtle.presenter.RecommentPresenter;
import com.squrtle.presenter.contract.RecommendContract;
import com.squrtle.ui.adapter.RecommendAppAdapter;
import com.squrtle.ui.bean.AppInfo;
import com.squrtle.ui.bean.PageBean;
import com.squrtle.http.ApiService;
import com.squrtle.http.HttpManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends Fragment implements RecommendContract.View{
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private List<AppInfo> datas;
    private RecommendAppAdapter mAdapter;

    @Inject
    RecommendContract.Presenter mPresent;

    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomend, container, false);

        ButterKnife.bind(this, view);
        DaggerRecommendComponent.builder().recommendModule(new RecommendModule(this)).build().inject(this);

        mProgressDialog = new ProgressDialog(getActivity());


        //mPresent = new RecommentPresenter(this);
        initData();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        mPresent.requestDatas();

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
