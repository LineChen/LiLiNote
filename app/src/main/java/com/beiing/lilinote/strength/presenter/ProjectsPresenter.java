package com.beiing.lilinote.strength.presenter;

import android.content.Context;

import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.strength.view.IProjectsView;

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


    public ProjectsPresenter(Context mContext, IProjectsView mView) {
        super(mContext, mView);
        strengthItemList = new ArrayList<>();
    }


    public List<StrengthItem> getStrengthItemList() {
        return strengthItemList;
    }
}
