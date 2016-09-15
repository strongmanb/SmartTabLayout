package com.mars.tabdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by strongman on 2016/9/13.
 */
public class CommonFragment extends Fragment {

    public static CommonFragment newInstance(String text) {
        CommonFragment commonFragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        commonFragment.setArguments(bundle);
        return commonFragment;
    }

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        tv = (TextView) rootView.findViewById(R.id.tv);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String text = getArguments().getString("text");
        tv.setText(text);
    }


    /*@Override
    public void initViewsAndEvents() {
        String text = getArguments().getString("text");
        tv = findViewById(R.id.tv);
        tv.setText(text);
        Log.i("myTestLog333", "fragment:" + text + "，开始加载数据");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_layout;
    }*/
}
