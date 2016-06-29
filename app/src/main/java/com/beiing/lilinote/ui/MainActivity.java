package com.beiing.lilinote.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.beiing.lilinote.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.common_toolbar)
    Toolbar commonToolbar;
    @Bind(R.id.navigationView)
    NavigationView navigationView;

    ActionBarDrawerToggle mToggle;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar(commonToolbar);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mToggle != null) {
            mToggle.onConfigurationChanged(newConfig);
        }
    }

    /**
     * 初始化ToolBar
     * @param toolbar
     */
    protected void initToolBar(Toolbar toolbar) {
        if (toolbar == null) return;
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 获取主题颜色
     *
     * @return
     */
    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


    /**
     * api大于19的时候，实现沉浸式状态栏
     */
    @TargetApi(19)
    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //配合 初始化主题设置主体颜色
    protected int getStatusBarColor() {
        return getColorPrimary();
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
