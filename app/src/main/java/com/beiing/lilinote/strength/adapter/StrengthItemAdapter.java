package com.beiing.lilinote.strength.adapter;

import android.content.Context;
import android.view.View;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_listview.CommonAdapter;
import com.beiing.baseframe.supports.OnClickListener;
import com.beiing.baseframe.utils.FileUtil;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.felipecsl.gifimageview.library.GifImageView;

import java.util.List;

/**
 * Created by chenliu on 2016/10/27.<br/>
 * 描述：
 * </br>
 */
public class StrengthItemAdapter extends CommonAdapter<StrengthItem>{

    OnClickListener<StrengthItem> onClickListener;

    public StrengthItemAdapter(Context context, List<StrengthItem> datas) {
        super(context, R.layout.item_strength_item, datas);
    }

    @Override
    public void bindItemView(final ViewHolder holder, final StrengthItem item) {
        GifImageView gifView = holder.getView(R.id.gif_view);
        byte[] fileBytes = FileUtil.getFileBytes(item.getResPath());
        if (fileBytes != null) {
            gifView.setBytes(fileBytes);
            gifView.startAnimation();
        }

        holder.setText(R.id.tv_project_kind_name, item.getName());

        holder.setText(R.id.tv_project_count, String.valueOf(item.getCount()));
        holder.setText(R.id.tv_project_time, String.valueOf(item.getDistrictTime()));

        holder.setOnClickListener(R.id.iv_remove, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getmPosition(), R.id.iv_remove, item);
                }
            }
        });

        holder.setOnClickListener(R.id.ll_count, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getmPosition(), R.id.ll_count, item);
                }
            }
        });

        holder.setOnClickListener(R.id.ll_time, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getmPosition(), R.id.ll_time, item);
                }
            }
        });

    }

    public void setOnClickListener(OnClickListener<StrengthItem> onClickListener) {
        this.onClickListener = onClickListener;
    }
}
