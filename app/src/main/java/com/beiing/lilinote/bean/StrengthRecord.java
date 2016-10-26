package com.beiing.lilinote.bean;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：锻炼项目
 * </br>
 */

@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthRecord extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String date;//日期

    @Column
    String tag;//标签

    @Column
    String note;//备注

    @Column
    String strengthItemsJson;//项目集合json字符串


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStrengthItemsJson() {
        return strengthItemsJson;
    }

    public void setStrengthItemsJson(String strengthItemsJson) {
        this.strengthItemsJson = strengthItemsJson;
    }
}
