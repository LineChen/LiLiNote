package com.beiing.lilinote.strength.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beiing.baseframe.widgets.MultiListView;
import com.beiing.lilinote.R;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.setting.SettingActivity;

import base.activity.BaseActivity;
import butterknife.Bind;

public class AddStrengthActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.lv_projects)
    MultiListView lvProject;

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
        return R.layout.activity_add_strength;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("添加");
    }


    /**
     * 添加项目
     * @param view
     */
    public void addProjectItem(View view) {
        Intent intent = new Intent(this, AddStrengthActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_STRENGTH_PROJECT);
    }
}
