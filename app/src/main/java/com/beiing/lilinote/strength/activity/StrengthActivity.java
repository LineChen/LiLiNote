package com.beiing.lilinote.strength.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.setting.SettingActivity;
import com.beiing.lilinote.strength.adapter.RecordAdapter;
import com.beiing.lilinote.strength.presenter.StrengthRecordPresenter;
import com.beiing.lilinote.strength.view.IStrengthRecordView;

import base.activity.BaseActivity;
import butterknife.Bind;

public class StrengthActivity extends BaseActivity implements IStrengthRecordView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_strength_list)
    RecyclerView rvStrengthList;

    RecordAdapter adapter;

    StrengthRecordPresenter presenter;

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
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_strength;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("完成列表");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_add:
                startActivity(new Intent(this, AddStrengthActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initPresenter() {
        presenter = new StrengthRecordPresenter(this, this);
    }

    @Override
    protected void initData() {
        adapter = new RecordAdapter(this, presenter.getRecords());
        rvStrengthList.setLayoutManager(new LinearLayoutManager(this));
        rvStrengthList.setAdapter(adapter);

        presenter.loadRecords();
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<StrengthRecord>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthRecord strengthRecord, int position) {

            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, StrengthRecord strengthRecord, int position) {
                return false;
            }
        });
    }

    @Override
    public void loadResult(boolean loaded) {
        if(loaded){
            adapter.notifyDataSetChanged();
        }
    }
}
