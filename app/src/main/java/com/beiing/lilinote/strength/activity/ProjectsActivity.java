package com.beiing.lilinote.strength.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.adapter.ProjectsAdapter;
import com.beiing.lilinote.strength.presenter.ProjectsPresenter;
import com.beiing.lilinote.strength.view.IProjectsView;

import base.activity.BaseActivity;
import butterknife.Bind;

public class  ProjectsActivity extends BaseActivity implements IProjectsView{
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_done, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_done:
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(Constant.INTENT_SELECT_PROJECTS, presenter.getSelectList());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void initData() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectsAdapter(this, presenter.getStrengthItemList());
        rvProjects.setAdapter(adapter);
        presenter.loadProjects();
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<StrengthItem>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthItem strengthItem, int position) {
                if (strengthItem.isSelect()) {
                    strengthItem.setSelect(false);
                    presenter.removeSelect(strengthItem);
                } else {
                    strengthItem.setSelect(true);
                    presenter.addSelect(strengthItem);
                }
                adapter.notifyItemChanged(position);
            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, StrengthItem strengthItem, int position) {
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
