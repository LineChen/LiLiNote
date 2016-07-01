package com.beiing.lilinote.gifmake;

import base.mvp.IBaseView;

/**
 * Created by chenliu on 2016/7/1.<br/>
 * 描述：
 * </br>
 */
public interface IGifMakeView extends IBaseView {
    void finishPaths();

    void finishCreate(boolean b);
}
