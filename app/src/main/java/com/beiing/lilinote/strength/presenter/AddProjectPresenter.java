package com.beiing.lilinote.strength.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.beiing.baseframe.utils.FileUtil;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.constant.Constant;
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

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}













