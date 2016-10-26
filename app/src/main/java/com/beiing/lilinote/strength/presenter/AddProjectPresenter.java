package com.beiing.lilinote.strength.presenter;

import android.content.Context;

import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.strength.view.IAddProjectView;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/26 0026<br/>.
 * 描述：
 */
public class AddProjectPresenter extends BasePresenter<IAddProjectView> {
    String path;

    public AddProjectPresenter(Context mContext, IAddProjectView mView) {
        super(mContext, mView);
    }


    public void addProject(String name){
        StrengthItem item = new StrengthItem();
        item.setName(name);
        item.setResPath(path);
        item.insert();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}













