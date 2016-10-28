package com.beiing.lilinote.strength.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_recyclerview.adapter.CommonAdapter;
import com.beiing.baseframe.adapter.for_recyclerview.support.ItemSupport;
import com.beiing.baseframe.utils.TimeUtil;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.utils.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenliu on 2016/10/27.<br/>
 * 描述：
 * </br>
 */
public class RecordAdapter extends CommonAdapter<StrengthRecord> {
    public RecordAdapter(Context context, List<StrengthRecord> datas) {
        super(context, datas, new ItemSupport<StrengthRecord>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_strength;
            }
        });
    }

    @Override
    protected void bindItemView(ViewHolder holder, StrengthRecord record) {
        try {
            int position = holder.getLayoutPosition();
            String month = TimeUtil.getMonthStr(record.getDate(), TimeUtil.DATE_FORMAT_8);
            if(position == 0){
                holder.setVisible(R.id.ll_month, true);
                holder.setText(R.id.tv_month, month);
            } else {
                    String lastMonth = TimeUtil.getMonthStr(getDatas().get(position - 1).getDate(), TimeUtil.DATE_FORMAT_8);
                    if(month.equals(lastMonth)){
                        holder.setVisible(R.id.ll_month, false);
                    } else{
                        holder.setVisible(R.id.ll_month, true);
                        holder.setText(R.id.tv_month, month);
                    }
            }

            holder.setText(R.id.tv_date, record.getDate());

            List<StrengthItem> items = GsonUtil.gsonToList(record.getStrengthItemsJson(), StrengthItem.class);
            StringBuilder sb = new StringBuilder();
            for (StrengthItem item :
                    items) {
                sb.append(item.getName()).append(" × ").append(item.getCount()).append("\n");
            }
            String projects = sb.toString();
            holder.setText(R.id.tv_projects, projects.substring(0, projects.length() - 1));

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}











