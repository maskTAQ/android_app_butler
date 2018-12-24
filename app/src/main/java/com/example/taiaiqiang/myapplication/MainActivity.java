package com.example.taiaiqiang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.taiaiqiang.myapplication.fragment.Butler;
import com.example.taiaiqiang.myapplication.fragment.T;
import com.example.taiaiqiang.myapplication.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener,ViewPager.OnPageChangeListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> title;
    private List<Fragment> fragment;
    private FloatingActionButton fixed_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        title = new ArrayList<>();
        title.add("管家服务");
        title.add("T");

        fragment = new ArrayList<>();
        fragment.add(new Butler());
        fragment.add(new T());
    }

    private void initView() {
        fixed_button =(FloatingActionButton) findViewById(R.id.fixed_button);
        fixed_button.setOnClickListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fixed_button.setVisibility(View.GONE);
        viewPager.addOnPageChangeListener(this);

        //预加载
        viewPager.setOffscreenPageLimit(fragment.size());
        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragment.get(position);
            }

            @Override
            public int getCount() {
                return fragment.size();
            }
            //设置标题

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }
   @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.fixed_button:
                startActivity(new Intent(this,SettingActivity.class));
        }
   }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到
//
//            调用。其中三个参数的含义分别为：
//
//            position :当前页面，及你点击滑动的页面
//
//            positionOffset:当前页面偏移的百分比
//
//            positionOffsetPixels:当前页面偏移的像素位置
    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            fixed_button.setVisibility(View.GONE);
        }else{
            fixed_button.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
    }

}
