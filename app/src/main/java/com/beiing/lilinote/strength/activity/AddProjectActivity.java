package com.beiing.lilinote.strength.activity;

import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.adapter.ProjectsAdapter;
import com.beiing.lilinote.strength.presenter.AddProjectPresenter;
import com.beiing.lilinote.strength.view.IAddProjectView;
import com.bumptech.glide.Glide;
import com.cocosw.bottomsheet.BottomSheet;

import java.io.File;
import java.util.List;

import base.activity.BaseActivity;
import base.image_selector.MultiImageSelector;
import butterknife.Bind;

public class AddProjectActivity extends BaseActivity implements IAddProjectView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.iv_sample)
    ImageView ivSample;

    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.rv_projects)
    RecyclerView rvProjects;

    ProjectsAdapter adapter;

    AddProjectPresenter presenter;

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
        return R.layout.activity_add_project;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("添加项目");
    }

    @Override
    protected void initPresenter() {
        presenter = new AddProjectPresenter(this, this);
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
                String name = etName.getText().toString();
                if(TextUtils.isEmpty(presenter.getPath())){
                    Toast.makeText(AddProjectActivity.this, "请选择示例图", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(AddProjectActivity.this, "请填写名称", Toast.LENGTH_SHORT).show();
                }
                presenter.addProject(name);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectsAdapter(this, presenter.getStrengthItemList());
        adapter.setShowCheckBox(false);
        rvProjects.setAdapter(adapter);
        presenter.loadProjects();
    }

    @Override
    protected void initEvent() {
        ivSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create()
                        .showCamera(true) // show camera or not. true by default
                        .single() // single mode, default mode;
                        .start(AddProjectActivity.this, Constant.REQUEST_CODE_SELECT_IMAGE);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener<StrengthItem>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthItem item, int position) {

            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, final StrengthItem item, final int position) {
                BottomSheet.Builder builder = new BottomSheet
                        .Builder(AddProjectActivity.this, com.cocosw.bottomsheet.R.style.BottomSheet_Dialog)
                        .title("确定删除这个项目?");
                builder.sheet(0, "确定").sheet(1, "取消")
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    presenter.delete(item);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }).build().show();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.REQUEST_CODE_SELECT_IMAGE){
            if(resultCode == RESULT_OK){
                List<String> paths = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                if (paths != null && paths.size() > 0) {
                    presenter.setPath(paths.get(0));
                    Glide.with(this).load(new File(paths.get(0))).into(ivSample);
                }
            }
        }
    }


    @Override
    public void addResult(boolean added) {
        if(added){
            presenter.loadProjects();
            Toast.makeText(AddProjectActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            presenter.setPath("");
            ivSample.setImageResource(R.mipmap.icon_plus);
            etName.setText("");
        }
    }

    @Override
    public void loadResult(boolean loaded) {
        if(loaded){
            adapter.notifyDataSetChanged();
        }
    }
}
