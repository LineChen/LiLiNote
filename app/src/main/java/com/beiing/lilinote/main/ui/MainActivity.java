package com.beiing.lilinote.main.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.beiing.lilinote.R;

import base.activity.BaseActivity;
import base.image_selector.MultiImageSelector;
import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar commonToolbar;
    @Bind(R.id.navigationView)
    NavigationView navigationView;

    ActionBarDrawerToggle mToggle;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handArrow();
        initNavigationView();
    }

    /**
     * 初始化侧边栏布局
     */
    private void initNavigationView() {
        navigationView.setCheckedItem(R.id.nav_default);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_default:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_note:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_note_diary:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_share:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        MultiImageSelector.create()
                                .showCamera(true) // show camera or not. true by default
                        .count(3) // max select image size, 9 by default. used width #.multi()
                        .single() // single mode
                            .multi() // multi mode, default mode;
//                            .origin() // original select data set, used width #.multi()
                            .start(MainActivity.this, 100);
                        break;

                    case R.id.nav_setting:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    private void handArrow() {
        // 旋转toolbar箭头
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, commonToolbar, R.string.app_name, R.string.app_name);
        mToggle.syncState();
        drawerLayout.setDrawerListener(mToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mToggle != null) {
            mToggle.syncState();
        }
    }

    @Override
    public boolean initSwipeBackEnable() {
        return false;
    }

    @Override
    public void initToolBar() {
        super.initToolBar(commonToolbar);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mToggle != null) {
            mToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                return true;
            case R.id.about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
