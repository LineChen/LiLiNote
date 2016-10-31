package com.beiing.lilinote.strength.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.beiing.baseframe.widgets.DefaultRefreshLayout;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.setting.SettingActivity;
import com.beiing.lilinote.strength.adapter.RecordAdapter;
import com.beiing.lilinote.strength.presenter.StrengthRecordPresenter;
import com.beiing.lilinote.strength.view.IStrengthRecordView;
import com.beiing.lilinote.utils.DialogUtil;
import com.cocosw.bottomsheet.BottomSheet;

import base.activity.BaseActivity;
import butterknife.Bind;

/**
 * 健身列表
 */
public class StrengthActivity extends BaseActivity implements IStrengthRecordView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.srl_refresh_layout)
    DefaultRefreshLayout refreshLayout;
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
        return true;
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
                Intent intent = new Intent(mContext, AddStrengthActivity.class);
                intent.putExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_ADD);
                startActivityForResult(intent, Constant.STRENGTH_MODE_ADD);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == Constant.STRENGTH_MODE_ADD){
                presenter.loadRecords();
            }
        }
    }

    @Override
    protected void initPresenter() {
        presenter = new StrengthRecordPresenter(this, this);
    }

    @Override
    protected void initData() {
        rvStrengthList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordAdapter(this, presenter.getRecords());
        rvStrengthList.setAdapter(adapter);

        DialogUtil.showLoading(this);
        presenter.loadRecords();
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<StrengthRecord>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthRecord strengthRecord, int position) {
                Intent intent = new Intent(mContext, AddStrengthActivity.class);
                intent.putExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_EDIT);
                intent.putExtra(Constant.INTENT_STRENGTH_RECORD, strengthRecord);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, final StrengthRecord strengthRecord, final int position) {
                BottomSheet.Builder builder = new BottomSheet
                        .Builder(StrengthActivity.this, com.cocosw.bottomsheet.R.style.BottomSheet_Dialog)
                        .title("确定删除这条记录?");
                builder.sheet(0, "确定").sheet(1, "取消")
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    presenter.delete(strengthRecord);
                                    adapter.getDatas().remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }).build().show();
                return false;
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRecords();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void loadResult(boolean loaded) {
        if(loaded){
            adapter.notifyDataSetChanged();
        }
        DialogUtil.dimiss();
    }
}
