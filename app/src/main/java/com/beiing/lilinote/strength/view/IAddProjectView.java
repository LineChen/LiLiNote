package com.beiing.lilinote.strength.view;

import base.mvp.IBaseView;

/**
 * Created by chenliu on 2016/10/26 0026<br/>.
 * 描述：
 */
public interface IAddProjectView extends IBaseView {
    void addResult(boolean added);

    void loadResult(boolean loaded);
}
