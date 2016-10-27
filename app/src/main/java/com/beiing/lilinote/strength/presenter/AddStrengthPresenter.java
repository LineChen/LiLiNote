package com.beiing.lilinote.strength.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthRecord;
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


    public AddStrengthPresenter(Context mContext, IAddStrengthView mView) {
        super(mContext, mView);
        projects = new ArrayList<>();
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

    public List<StrengthItem> getProjects() {
        return projects;
    }

}
