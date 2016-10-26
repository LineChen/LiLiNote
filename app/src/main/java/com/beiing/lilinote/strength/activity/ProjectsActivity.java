package com.beiing.lilinote.strength.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.strength.adapter.ProjectsAdapter;
import com.beiing.lilinote.strength.presenter.ProjectsPresenter;
import com.beiing.lilinote.strength.view.IProjectsView;

import base.activity.BaseActivity;
import butterknife.Bind;

public class ProjectsActivity extends BaseActivity implements IProjectsView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.rv_projects)
    RecyclerView rvProjects;

    ProjectsPresenter presenter;

    ProjectsAdapter adapter;

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
        return R.layout.activity_projects;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("项目列表");
    }

    @Override
    protected void initPresenter() {
        presenter = new ProjectsPresenter(this, this);
    }

    @Override
    protected void initData() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectsAdapter(this, presenter.getStrengthItemList());
        rvProjects.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<StrengthItem>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthItem strengthItem, int position) {
                if (strengthItem.isSelect()) {
                    strengthItem.setSelect(false);
                } else {
                    strengthItem.setSelect(true);
                }
                adapter.notifyItemChanged(position);
            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, StrengthItem strengthItem, int position) {
                return false;
            }
        });
    }
}
