package com.beiing.lilinote.strength.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.baseframe.supports.OnClickListener;
import com.beiing.baseframe.utils.TimeUtil;
import com.beiing.baseframe.widgets.MultiListView;
import com.beiing.baseframe.widgets.NumControlledEditText;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.adapter.StrengthItemAdapter;
import com.beiing.lilinote.strength.presenter.AddStrengthPresenter;
import com.beiing.lilinote.strength.view.IAddStrengthView;
import com.beiing.lilinote.utils.DialogUtil;
import com.beiing.lilinote.utils.GsonUtil;

import java.io.IOException;
import java.util.List;

import base.activity.BaseActivity;
import butterknife.Bind;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 添加健身记录
 */
public class AddStrengthActivity extends BaseActivity implements IAddStrengthView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tv_date)
    TextView tvDate;

    @Bind(R.id.tv_tag)
    TextView tvTag;

    @Bind(R.id.lv_projects)
    MultiListView lvProject;

    @Bind(R.id.nce_note)
    NumControlledEditText controlledEditText;

    AddStrengthPresenter presenter;

    StrengthItemAdapter adapter;

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
        return R.layout.activity_add_strength;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle("添加");
    }

    @Override
    protected void initPresenter() {
        presenter = new AddStrengthPresenter(this, this);
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
                DialogUtil.showLoading(this);
                int mode = presenter.getMode();
                switch (mode){
                    case Constant.STRENGTH_MODE_ADD:
                        presenter.saveRecord(tvDate.getText().toString(), tvTag.getText().toString(), controlledEditText.getText());
                        break;

                    case Constant.STRENGTH_MODE_EDIT:
                        presenter.updateRecord(tvDate.getText().toString(), tvTag.getText().toString(), controlledEditText.getText());
                        break;

                    case Constant.STRENGTH_MODE_PLAN_ADD:

                        break;

                    case Constant.STRENGTH_MODE_PLAN_EDIT:

                        break;
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == Constant.REQUEST_CODE_SELECT_STRENGTH_PROJECT){
                List<StrengthItem> items = data.getParcelableArrayListExtra(Constant.INTENT_SELECT_PROJECTS);
                presenter.addProjects(items);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initData() {
        tvDate.setText(TimeUtil.getFormatDate(System.currentTimeMillis(), TimeUtil.DATE_FORMAT_8));
        adapter = new StrengthItemAdapter(this, presenter.getProjects());
        lvProject.setAdapter(adapter);

        presenter.getIntentData();

        int mode = presenter.getMode();
        switch (mode){
            case Constant.STRENGTH_MODE_ADD:
                toolbar.setTitle("添加");
                break;

            case Constant.STRENGTH_MODE_EDIT:
                toolbar.setTitle("修改");
                break;

            case Constant.STRENGTH_MODE_PLAN_ADD:
                toolbar.setTitle("添加计划");
                break;

            case Constant.STRENGTH_MODE_PLAN_EDIT:
                toolbar.setTitle("修改计划");
                break;
        }

    }

    @Override
    protected void initEvent() {
        adapter.setOnClickListener(new OnClickListener<StrengthItem>() {
            @Override
            public void onClick(int position, int id, final StrengthItem item) {
                switch (id){
                    case R.id.iv_remove:
                        presenter.removeProject(item);
                        adapter.notifyDataSetChanged();
                        break;

                    case R.id.ll_count:
                        NumberPicker countp = new NumberPicker(AddStrengthActivity.this);
                        countp.setRange(5, 500, 5);
                        countp.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(String option) {
                                item.setCount(Integer.valueOf(option));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        countp.show();
                        break;

                    case R.id.ll_time:
                        NumberPicker nump = new NumberPicker(AddStrengthActivity.this);
                        nump.setRange(1, 100);
                        nump.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(String option) {
                                item.setDistrictTime(Integer.valueOf(option));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        nump.show();
                        break;

                }

            }
        });
    }

    @OnClick({R.id.tv_add_project, R.id.tv_date})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_add_project:
                Intent intent = new Intent(this, ProjectsActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_STRENGTH_PROJECT);
                break;

            case R.id.tv_date:
                DatePicker picker = new DatePicker(this);
                int endYear = TimeUtil.getYear(System.currentTimeMillis());
                picker.setRange(endYear - 1, endYear);//年份范围
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String date = year + "." + month + "." + day;
                        tvDate.setText(date);
                    }
                });
                picker.show();
                break;
        }
    }

    @Override
    public void initRecord(StrengthRecord record) {
        if (record != null) {
            tvDate.setText(record.getDate());
            tvTag.setText(record.getTag());
            controlledEditText.setText(record.getNote());
            try {
                List<StrengthItem> items = GsonUtil.gsonToList(record.getStrengthItemsJson(), StrengthItem.class);
                presenter.addProjects(items);
                adapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addResult(boolean added) {
        DialogUtil.dimiss();
        if(added){
            Toast.makeText(AddStrengthActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddStrengthActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
        }
    }
}
