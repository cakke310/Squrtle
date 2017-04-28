package com.squrtle.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.squrtle.R;
import com.squrtle.bean.AppInfo;
import com.squrtle.bean.PageBean;
import com.squrtle.di.component.AppComponent;
import com.squrtle.presenter.AppInfoPresenter;
import com.squrtle.presenter.contract.AppInfoContract;
import com.squrtle.ui.activity.AppDetailActivity;
import com.squrtle.ui.adapter.AppInfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by c_xuwei-010 on 2017/4/24.
 */
public abstract class BaseAppInfoFragment extends BaseFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    int page = 0;
    private AppInfoAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        mPresenter.requestData(type(),page);
        initRecyclerView();
    }

    protected void initRecyclerView(){
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();
        mAdapter.setOnLoadMoreListener(this);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppInfo appInfo = mAdapter.getItem(position);

                mApplication.setView(view);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra("appinfo",appInfo);
                startActivity(intent);

            }
        });
    }

    abstract int type();

    abstract AppInfoAdapter buildAdapter();

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if(pageBean.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(),page);
    }

}
