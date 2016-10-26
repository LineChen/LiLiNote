package com.beiing.lilinote.strength.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_recyclerview.adapter.CommonAdapter;
import com.beiing.baseframe.adapter.for_recyclerview.support.ItemSupport;
import com.beiing.baseframe.utils.FileUtil;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.StrengthItem;
import com.felipecsl.gifimageview.library.GifImageView;

import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by chenliu on 2016/10/26.<br/>
 * 描述：
 * </br>
 */
public class ProjectsAdapter extends CommonAdapter<StrengthItem> {
    public ProjectsAdapter(Context context, List<StrengthItem> datas) {
        super(context, datas, new ItemSupport<StrengthItem>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_project;
            }
        });
    }

    @Override
    protected void bindItemView(ViewHolder holder, StrengthItem strengthItem) {
        holder.setText(R.id.tv_project_kind_name, strengthItem.getName());

        SmoothCheckBox scb = holder.getView(R.id.scb_icon_select);
        scb.setChecked(strengthItem.isSelect());

        GifImageView gifView = holder.getView(R.id.gif_view);
        byte[] fileBytes = FileUtil.getFileBytes(strengthItem.getResPath());
        if (fileBytes != null) {
            gifView.setBytes(fileBytes);
            gifView.startAnimation();
        }
    }

}


