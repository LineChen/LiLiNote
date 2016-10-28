package com.beiing.lilinote.strength.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.beiing.baseframe.utils.FileUtil;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.view.IAddProjectView;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/26 0026<br/>.
 * 描述：
 */
public class AddProjectPresenter extends BasePresenter<IAddProjectView> {
    String path;

    private List<StrengthItem> strengthItemList;

    public AddProjectPresenter(Context mContext, IAddProjectView mView) {
        super(mContext, mView);
        strengthItemList = new ArrayList<>();
    }

    public void addProject(String name){
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(path)){
            byte[] fileBytes = FileUtil.getFileBytes(path);

            String newpath = FileUtil.saveFile(fileBytes, Constant.STRENGTH_DIR, System.currentTimeMillis() + ".gif");

            StrengthItem item = new StrengthItem();
            item.setName(name);
            item.setResPath(newpath);
            item.insert();
            mView.addResult(true);
        }
    }

    public void loadProjects(){
        List<StrengthItem> projects = new Select().from(StrengthItem.class).queryList();
        if (projects != null) {
            strengthItemList.clear();
            strengthItemList.addAll(projects);
            mView.loadResult(true);
        }
    }


    public List<StrengthItem> getStrengthItemList() {
        return strengthItemList;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void delete(StrengthItem item) {
        item.delete();
        if(strengthItemList.contains(item)){
            strengthItemList.remove(item);
        }
    }
}













