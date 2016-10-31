package com.beiing.lilinote.strength.view;

import com.beiing.lilinote.bean.StrengthPlan;
import com.beiing.lilinote.bean.StrengthRecord;

import base.mvp.IBaseView;

/**
 * Created by chenliu on 2016/10/27.<br/>
 * 描述：
 * </br>
 */
public interface IAddStrengthView extends IBaseView{
    void addResult(boolean added);

    void initRecord(StrengthRecord record);

    void initPlan(StrengthPlan plan);
}
