package com.mars.tabdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.strongman.tablayout.CommonScrollTabLayout;
import com.strongman.tablayout.CommonTabLayout;
import com.strongman.tablayout.SlidingTabLayout;
import com.strongman.tablayout.listener.CustomTabEntity;
import com.strongman.tablayout.listener.OnTabSelectListener;
import com.strongman.tablayout.listener.impl.TabEntity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private ArrayList<Fragment> mScrollFragments = new ArrayList<>();

    private CommonTabLayout tabLayout;
    private CommonTabLayout tabLayout2;
    private CommonTabLayout tabLayout3;
    private CommonScrollTabLayout scrollTabLayout;
    private SlidingTabLayout slidingTabLayout;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (CommonTabLayout) findViewById(R.id.tablayout1);
        tabLayout2 = (CommonTabLayout) findViewById(R.id.tablayout2);
        tabLayout3 = (CommonTabLayout) findViewById(R.id.tab_layout_3);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        //init fragments
        for (int i=1; i<=mTitles.length; i++) {
            mFragments.add(CommonFragment.newInstance("tab" + i));
        }
        //init fragments2
        for (int i=1; i<=mTitles.length; i++) {
            mFragments2.add(CommonFragment.newInstance("tab" + i));
        }
        //init titles
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        initViewPageMode();
        initCommonModel();
        initScrollTabLayout();
        initBlockTabLayout();
        initSlidingTabLayout();
    }

    private void initBlockTabLayout() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>(2);
        mTabEntities.add(new TabEntity("收件箱", 0, 0));
        mTabEntities.add(new TabEntity("发件箱", 0, 0));
        mTabEntities.add(new TabEntity("发件箱2", 0, 0));
        mTabEntities.add(new TabEntity("发件箱3", 0, 0));
        tabLayout3.setTabData(mTabEntities);
    }


    /**
     * use viewpage mode
     */
    private void initViewPageMode() {
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {

                }
            }
        });


        tabLayout.showMsg(1, 10);
        tabLayout.setMsgMargin(1, -9, 0);

        tabLayout.showMsg(2, "new");
        tabLayout.setMsgMargin(2, -16, 0);
        tabLayout.setMsgTextSize(2, 12);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);


    }


    /**
     * use common mode
     */
    private void initCommonModel() {
       tabLayout2.setTabData(mTabEntities, this, R.id.fl_change, mFragments2);
       tabLayout2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //tabLayout2.setCurrentTab(1);
    }


    private void initScrollTabLayout() {
        String[] titles = {"首页", "消息", "联系人", "更多", "首页2", "消息2", "联系人2", "更多2"};
        int[] iconUnselectIds = {
                R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
                R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect,
                R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
                R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
        int[] iconSelectIds = {
                R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
                R.mipmap.tab_contact_select, R.mipmap.tab_more_select,
                R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
                R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
        //init scroll fragments
        for (int i=1; i<=titles.length; i++) {
            mScrollFragments.add(CommonFragment.newInstance("tab" + i));
        }

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            tabEntities.add(new TabEntity(titles[i], iconSelectIds[i], iconUnselectIds[i]));
        }

        scrollTabLayout = (CommonScrollTabLayout) findViewById(R.id.scroll_tablayout);
        scrollTabLayout.setTabData(tabEntities, this, R.id.scroll_fl, mScrollFragments);
    }


    private void initSlidingTabLayout() {
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        slidingTabLayout.setViewPager(mViewPager);
        Button left = (Button) findViewById(R.id.btn1);
        Button right = (Button) findViewById(R.id.btn2);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leftPosition = slidingTabLayout.getCurrentTab() == 0 ? 0 : slidingTabLayout.getCurrentTab() - 1;
                slidingTabLayout.setCurrentTab(leftPosition);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rightPosition = slidingTabLayout.getCurrentTab() == 6 ? 6 : slidingTabLayout.getCurrentTab() + 1;
                slidingTabLayout.setCurrentTab(rightPosition);
            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
