package com.beiing.lilinote.strength.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beiing.lilinote.R;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.presenter.AddProjectPresenter;
import com.beiing.lilinote.strength.view.IAddProjectView;
import com.bumptech.glide.Glide;

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
        super.initData();
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
            Toast.makeText(AddProjectActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            presenter.setPath("");
            ivSample.setImageResource(R.mipmap.icon_plus);
            etName.setText("");
        }
    }
}
