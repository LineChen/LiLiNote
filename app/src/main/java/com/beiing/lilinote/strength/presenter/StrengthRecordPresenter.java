package com.beiing.lilinote.strength.presenter;

import android.content.Context;
import android.util.Log;

import com.beiing.baseframe.utils.TimeUtil;
import com.beiing.lilinote.bean.StrengthItem;
import com.beiing.lilinote.bean.StrengthRecord;
import com.beiing.lilinote.strength.view.IStrengthRecordView;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import base.mvp.BasePresenter;

/**
 * Created by chenliu on 2016/10/27.<br/>
 * 描述：
 * </br>
 */
public class StrengthRecordPresenter extends BasePresenter<IStrengthRecordView> {
    List<StrengthRecord> records;

    public StrengthRecordPresenter(Context mContext, IStrengthRecordView mView) {
        super(mContext, mView);
        records = new ArrayList<>();
    }

    public void loadRecords(){
        List<StrengthRecord> rds = new Select().from(StrengthRecord.class).queryList();
        Collections.sort(rds, new Comparator<StrengthRecord>() {
            @Override
            public int compare(StrengthRecord lhs, StrengthRecord rhs) {
                return rhs.getDate().compareToIgnoreCase(lhs.getDate());
            }
        });

        this.records.clear();
        this.records.addAll(rds);
        mView.loadResult(true);
    }

    public List<StrengthRecord> getRecords() {
        return records;
    }

    public void delete(StrengthRecord strengthRecord) {
        strengthRecord.delete();
    }
}
