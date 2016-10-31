package com.beiing.lilinote.strength.adapter;

import android.content.Context;
import android.view.View;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_recyclerview.adapter.CommonAdapter;
import com.beiing.baseframe.adapter.for_recyclerview.support.ItemSupport;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthPlan;
import com.beiing.lilinote.utils.GsonUtil;

import java.io.IOException;
import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by chenliu on 2016/10/31.<br/>
 * 描述：
 * </br>
 */
public class StrengthPlanAdapter extends CommonAdapter<StrengthPlan> {

    private boolean showCheckBox = true;

    public StrengthPlanAdapter(Context context, List<StrengthPlan> datas) {
        super(context, datas, new ItemSupport<StrengthPlan>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_strength_plan;
            }
        });
    }

    @Override
    protected void bindItemView(ViewHolder holder, StrengthPlan plan) {
        List<StrengthItem> items = null;
        try {
            items = GsonUtil.gsonToList(plan.getStrengthPlanJson(), StrengthItem.class);
            if (items != null) {
                StringBuilder sb = new StringBuilder();
                for (StrengthItem item :
                        items) {
                    sb.append(item.getName()).append(" × ").append(item.getCount()).append("\n");
                }
                String projects = sb.toString();
                holder.setText(R.id.tv_projects, projects.substring(0, projects.length() - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SmoothCheckBox scb = holder.getView(R.id.scb_icon_select);
        if(showCheckBox){
            scb.setVisibility(View.VISIBLE);
            scb.setChecked(plan.isSelect());
        } else {
            scb.setVisibility(View.GONE);
        }

    }


    public void setShowCheckBox(boolean showCheckBox) {
        this.showCheckBox = showCheckBox;
    }
}
