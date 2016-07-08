package com.beiing.lilinote.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.beiing.baseframe.supports.ThemeSelectListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.setting.ColorListAdapter;

import java.util.Arrays;
import java.util.List;

import base.utils.SPUtils;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by chenliu on 2016/7/6.<br/>
 * 描述：
 * </br>
 */
public class DialogUtil {

    private static MaterialDialog mDialog;

    public static void showLoading(Context context){
        mDialog = new MaterialDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.progressbar_item,
                        null);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setView(view).show();
    }

    public static void dimiss(){
        if(mDialog != null){
            mDialog.dismiss();
        }
    }

    public static void showColorSelectDialog(Context context, final ThemeSelectListener themeSelectListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("更换主题");
        Integer[] res = new Integer[]{R.drawable.round_red, R.drawable.round_brown, R.drawable.round_blue,
                R.drawable.round_blue_grey, R.drawable.round_yellow, R.drawable.round_deep_purple,
                R.drawable.round_pink, R.drawable.round_green};
        List<Integer> list = Arrays.asList(res);
        ColorListAdapter adapter = new ColorListAdapter(context, list);
        int value = (int) SPUtils.get(context, context.getResources().getString(R.string.change_theme_key), 7);
        adapter.setCheckItem(value);
        GridView gridView = (GridView) LayoutInflater.from(context).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                themeSelectListener.onThemeSelect(position);
                dialog.dismiss();
            }
        });
    }
}
