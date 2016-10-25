package com.beiing.lilinote.strength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beiing.lilinote.R;

import base.activity.BaseActivity;

public class StrengthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean openReceiver() {
        return true;
    }

    @Override
    protected boolean initSwipeBackEnable() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_strength;
    }

    @Override
    protected void initToolBar() {

    }
}
