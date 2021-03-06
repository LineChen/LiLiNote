package com.beiing.lilinote.main.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.beiing.lilinote.R;
import com.beiing.lilinote.gifmake.GifMakeActivity;
import com.beiing.lilinote.note.NoteEditActivity;
import com.beiing.lilinote.note.NoteListFragment;
import com.beiing.lilinote.setting.SettingActivity;
import com.beiing.lilinote.strength.activity.StrengthActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import base.activity.BaseActivity;
import butterknife.Bind;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar commonToolbar;
    @Bind(R.id.navigationView)
    NavigationView navigationView;

    ActionBarDrawerToggle mToggle;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fab_1)
    FloatingActionButton fab1;
    @Bind(R.id.fab_2)
    FloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handArrow();
        initNavigationView();

        initFragments();



    }

    private void initFragments() {
        NoteListFragment noteListFragment = NoteListFragment.getInstatnce();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content, noteListFragment);
        transaction.commit();
    }

    @Override
    protected void initEvent() {
        RxView.clicks(fab1).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivity(new Intent(mContext, NoteEditActivity.class));
            }
        });
    }

    @Override
    protected boolean openReceiver() {
        return true;
    }

    /**
     * 初始化侧边栏布局
     */
    private void initNavigationView() {
        navigationView.setCheckedItem(R.id.nav_note_diary);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_strength:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity.this, StrengthActivity.class));
                        break;

                    case R.id.nav_note:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_note_diary:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_mak_gif:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity.this, GifMakeActivity.class));
                        break;

                    case R.id.nav_setting:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
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
        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
