package com.squrtle.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.squrtle.bean.FragmentInfo;
import com.squrtle.ui.fragment.CategoryAppFragment;
import com.squrtle.ui.fragment.CategoryFragment;
import com.squrtle.ui.fragment.GamesFragment;
import com.squrtle.ui.fragment.RecommendFragment;
import com.squrtle.ui.fragment.TopListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c_xuwei-010 on 2017/4/5.
 */
public class CategoryAppViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>(3);
    private int CategoryId;
    private int FragmentType;

    public CategoryAppViewPagerAdapter(FragmentManager fm, int categoryId) {
        super(fm);
        this.CategoryId = categoryId;

        titles.add("精品");
        titles.add("排行");
        titles.add("新品");

    }



    @Override
    public Fragment getItem(int position) {
        return CategoryAppFragment.newInstance(CategoryId,position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
