package com.beiing.lilinote.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.beiing.baseframe.supports.ThemeSelectListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.activity.AddProjectActivity;
import com.beiing.lilinote.strength.activity.StrengthPlanActivity;
import com.beiing.lilinote.utils.DialogUtil;

import base.activity.BaseActivity;
import base.utils.SPUtils;
import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_change_theme)
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
    }

    @OnClick({R.id.tv_change_theme,
            R.id.tv_add_strength_project,
            R.id.tv_strength_plan_manage})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_change_theme:
                DialogUtil.showColorSelectDialog(this, new ThemeSelectListener() {
                    @Override
                    public void onThemeSelect(int position) {
                        SPUtils.put(SettingActivity.this, getResources().getString(R.string.change_theme_key), position);
                        sendNotification(new Intent(Constant.ACTION_CHANGE_THEME));
                    }
                });
                break;

            case R.id.tv_add_strength_project:
                startActivity(new Intent(this, AddProjectActivity.class));
                break;

            case R.id.tv_strength_plan_manage:
                Intent intent = new Intent(this, StrengthPlanActivity.class);
                intent.putExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_PLAN_ADD);
                startActivity(intent);
                break;

        }
    }


}














