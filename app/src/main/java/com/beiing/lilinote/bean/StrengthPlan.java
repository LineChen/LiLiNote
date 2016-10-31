package com.beiing.lilinote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by chenliu on 2016/10/31.<br/>
 * 描述：
 * </br>
 */
@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthPlan extends BaseModel implements Parcelable{

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String note;//备注

    @Column
    String strengthPlanJson;//计划集合json字符串

    boolean isSelect;

    public StrengthPlan(){

    }

    protected StrengthPlan(Parcel in) {
        id = in.readLong();
        note = in.readString();
        strengthPlanJson = in.readString();
    }

    public static final Creator<StrengthPlan> CREATOR = new Creator<StrengthPlan>() {
        @Override
        public StrengthPlan createFromParcel(Parcel in) {
            return new StrengthPlan(in);
        }

        @Override
        public StrengthPlan[] newArray(int size) {
            return new StrengthPlan[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStrengthPlanJson() {
        return strengthPlanJson;
    }

    public void setStrengthPlanJson(String strengthPlanJson) {
        this.strengthPlanJson = strengthPlanJson;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(note);
        dest.writeString(strengthPlanJson);
    }
}
