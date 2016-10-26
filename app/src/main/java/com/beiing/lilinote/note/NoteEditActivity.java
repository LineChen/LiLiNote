package com.beiing.lilinote.note;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.beiing.lilinote.R;

import base.activity.BaseActivity;
import butterknife.Bind;

/**
 * Created by chenliu on 2016/7/12.<br/>
 * 描述：编辑界面
 * </br>
 */
public class NoteEditActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

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
        return R.layout.activity_note_edit;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("编辑");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_done:
                Toast.makeText(NoteEditActivity.this, "ok", Toast.LENGTH_SHORT).show();
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}















