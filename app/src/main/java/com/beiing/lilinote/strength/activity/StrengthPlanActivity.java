package com.beiing.lilinote.strength.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthPlan;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.adapter.StrengthPlanAdapter;
import com.beiing.lilinote.strength.presenter.StrengthPlanPresenter;
import com.beiing.lilinote.strength.view.IStrengthPlanView;
import com.cocosw.bottomsheet.BottomSheet;

import base.activity.BaseActivity;
import butterknife.Bind;

public class StrengthPlanActivity extends BaseActivity implements IStrengthPlanView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.rv_plans)
    RecyclerView rvPlan;

    StrengthPlanPresenter presenter;

    StrengthPlanAdapter adapter;

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
        return R.layout.activity_strength_plan;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("计划管理");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(presenter.getMode() == Constant.STRENGTH_MODE_PLAN_CHOOSE){
            menu.findItem(R.id.edit_add).setTitle("确定");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_add:
                if(presenter.getMode() == Constant.STRENGTH_MODE_PLAN_ADD) {
                    Intent intent = new Intent(mContext, AddStrengthActivity.class);
                    intent.putExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_PLAN_ADD);
                    startActivityForResult(intent, Constant.STRENGTH_MODE_PLAN_ADD);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.INTENT_STRENGTH_PLAN, presenter.getSelecPlan());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.STRENGTH_MODE_PLAN_EDIT:
                case Constant.STRENGTH_MODE_PLAN_ADD:
                    presenter.loadPlan();
                    break;
            }
        }
    }

    @Override
    protected void initPresenter() {
       presenter = new StrengthPlanPresenter(this, this);
    }

    @Override
    protected void initData() {
        presenter.getIntentData();
        int mode = presenter.getMode();
        if(mode == Constant.STRENGTH_MODE_PLAN_ADD){
            toolbar.setTitle("计划管理");
        } else if(mode == Constant.STRENGTH_MODE_PLAN_CHOOSE){
            toolbar.setTitle("计划列表");
        }

        rvPlan.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StrengthPlanAdapter(this, presenter.getPlans());
        adapter.setShowCheckBox(mode == Constant.STRENGTH_MODE_PLAN_CHOOSE);
        rvPlan.setAdapter(adapter);
        presenter.loadPlan();
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<StrengthPlan>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, StrengthPlan strengthPlan, int position) {
                if(presenter.getMode() == Constant.STRENGTH_MODE_PLAN_ADD){
                    Intent intent = new Intent(mContext, AddStrengthActivity.class);
                    intent.putExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_PLAN_EDIT);
                    intent.putExtra(Constant.INTENT_STRENGTH_PLAN, strengthPlan);
                    startActivityForResult(intent, Constant.STRENGTH_MODE_PLAN_EDIT);
                } else {
                    presenter.setSelecPlan(strengthPlan);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, final StrengthPlan strengthPlan, int position) {
                if(presenter.getMode() == Constant.STRENGTH_MODE_PLAN_ADD) {
                    BottomSheet.Builder builder = new BottomSheet
                            .Builder(mContext, com.cocosw.bottomsheet.R.style.BottomSheet_Dialog)
                            .title("确定删除这个项目?");
                    builder.sheet(0, "确定").sheet(1, "取消")
                            .listener(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        presenter.delete(strengthPlan);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }).build().show();
                }
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
















