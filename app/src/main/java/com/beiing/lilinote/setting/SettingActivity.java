package com.beiing.lilinote.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.beiing.baseframe.supports.ThemeSelectListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.utils.DialogUtil;

import base.activity.BaseActivity;
import base.utils.SPUtils;
import butterknife.Bind;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_setting)
    TextView tvSetting;

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
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle(R.string.setting);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEvent() {
        tvSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting:
                DialogUtil.showColorSelectDialog(this, new ThemeSelectListener() {
                    @Override
                    public void onThemeSelect(int position) {
                        SPUtils.put(SettingActivity.this, getResources().getString(R.string.change_theme_key), position);
                        sendNotification(new Intent(Constant.ACTION_CHANGE_THEME));
                    }
                });
                break;
        }
    }


}














