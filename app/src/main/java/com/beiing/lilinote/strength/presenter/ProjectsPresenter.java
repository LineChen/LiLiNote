package com.beiing.lilinote.strength.presenter;

import android.content.Context;

import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.strength.view.IProjectsView;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/26.<br/>
 * 描述：
 * </br>
 */
public class ProjectsPresenter extends BasePresenter<IProjectsView> {

    private List<StrengthItem> strengthItemList;

    private ArrayList<StrengthItem> selectList;

    public ProjectsPresenter(Context mContext, IProjectsView mView) {
        super(mContext, mView);
        strengthItemList = new ArrayList<>();
        selectList = new ArrayList<>();
    }


    public void loadProjects(){
        List<StrengthItem> projects = new Select().from(StrengthItem.class).queryList();
        if (projects != null) {
            strengthItemList.addAll(projects);
            mView.loadResult(true);
        }
    }

    public void addSelect(StrengthItem item){
        if(!selectList.contains(item)){
            selectList.add(item);
        }
    }

    public void removeSelect(StrengthItem item){
        if(selectList.contains(item)){
            selectList.remove(item);
        }
    }

    public ArrayList<StrengthItem> getSelectList() {
        return selectList;
    }

    public List<StrengthItem> getStrengthItemList() {
        return strengthItemList;
    }
}
