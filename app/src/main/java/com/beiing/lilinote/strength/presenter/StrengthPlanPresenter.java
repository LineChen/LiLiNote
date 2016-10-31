package com.beiing.lilinote.strength.presenter;

import android.app.Activity;
import android.content.Context;

import com.beiing.lilinote.bean.StrengthPlan;
import com.beiing.lilinote.constant.Constant;
import com.beiing.lilinote.strength.view.IStrengthPlanView;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/31.<br/>
 * 描述：
 * </br>
 */
public class StrengthPlanPresenter extends BasePresenter<IStrengthPlanView> {

    List<StrengthPlan> plans;

    StrengthPlan selecPlan;

    int mode;//添加或者选择

    public StrengthPlanPresenter(Context mContext, IStrengthPlanView mView) {
        super(mContext, mView);
        plans = new ArrayList<>();
        selecPlan = new StrengthPlan();
    }

    public void getIntentData(){
        Activity activity = (Activity) mContext;
        mode = activity.getIntent().getIntExtra(Constant.INTENT_STRENGTH_RECORD_MODE, Constant.STRENGTH_MODE_PLAN_ADD);
    }

    public void loadPlan(){
        List<StrengthPlan> list = new Select().from(StrengthPlan.class).queryList();
        if(list != null){
            plans.clear();
            plans.addAll(list);
            mView.loadResult(true);
        }
    }

    public List<StrengthPlan> getPlans() {
        return plans;
    }

    public void delete(StrengthPlan strengthPlan) {
        if(plans.contains(strengthPlan)){
            plans.remove(strengthPlan);
        }
        strengthPlan.delete();
    }

    public int getMode() {
        return mode;
    }

    public void setSelecPlan(StrengthPlan selecPlan) {
        for (StrengthPlan plan :
                plans) {
            plan.setSelect(false);
        }
        selecPlan.setSelect(true);
        this.selecPlan = selecPlan;
    }

    public StrengthPlan getSelecPlan() {
        return selecPlan;
    }
}
