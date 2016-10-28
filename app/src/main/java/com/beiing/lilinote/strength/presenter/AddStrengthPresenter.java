package com.beiing.lilinote.strength.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.view.IAddStrengthView;
import com.beiing.lilinote.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/27.<br/>
 * 描述：
 * </br>
 */
public class AddStrengthPresenter extends BasePresenter<IAddStrengthView> {

    List<StrengthItem> projects;

    int mode;

    StrengthRecord record;

    public AddStrengthPresenter(Context mContext, IAddStrengthView mView) {
        super(mContext, mView);
        projects = new ArrayList<>();
    }


    public void getIntentData(){
        Activity activity = (Activity) mContext;
        Intent intent = activity.getIntent();
        mode = intent.getIntExtra(Constant.INTENT_STRENGTH_RECORD_MODE, -1);
        switch (mode){
            case Constant.STRENGTH_MODE_ADD:

                break;

            case Constant.STRENGTH_MODE_EDIT:
                record = intent.getParcelableExtra(Constant.INTENT_STRENGTH_RECORD);
                mView.initRecord(record);
                break;

            case Constant.STRENGTH_MODE_PLAN_ADD:

                break;

            case Constant.STRENGTH_MODE_PLAN_EDIT:

                break;
        }

    }


    public void addProjects(List<StrengthItem> items){
        for (StrengthItem item :
                items) {
            if(!projects.contains(item)){
                projects.add(item);
            }
        }
    }

    public void removeProject(StrengthItem item){
        if(projects.contains(item)){
            projects.remove(item);
        }
    }

    /**
     * 添加记录
     * @param date
     * @param tag
     * @param note
     */
    public void saveRecord(String date, String tag, String note) {
        if(TextUtils.isEmpty(date)){
            Toast.makeText(mContext, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }

        if(projects.size() == 0){
            Toast.makeText(mContext, "请至少添加一个项目", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            StrengthRecord record = new StrengthRecord();
            record.setDate(date);
            record.setTag(tag);
            record.setNote(note);
            String strengthItemsJson = GsonUtil.gsonToString(projects);
            record.setStrengthItemsJson(strengthItemsJson);
            record.insert();
            mView.addResult(true);
        } catch (Exception e){
            mView.addResult(false);
        }

    }

    /**
     * 修改记录
     * @param date
     * @param tag
     * @param note
     */
    public void updateRecord(String date, String tag, String note) {
        if(TextUtils.isEmpty(date)){
            Toast.makeText(mContext, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }

        if(projects.size() == 0){
            Toast.makeText(mContext, "请至少添加一个项目", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            record.setDate(date);
            record.setTag(tag);
            record.setNote(note);
            String strengthItemsJson = GsonUtil.gsonToString(projects);
            record.setStrengthItemsJson(strengthItemsJson);
            record.update();
            mView.addResult(true);
        } catch (Exception e){
            mView.addResult(false);
        }
    }

    public List<StrengthItem> getProjects() {
        return projects;
    }

    public int getMode() {
        return mode;
    }


}
