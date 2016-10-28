package com.beiing.lilinote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：一天的锻炼记录
 * </br>
 */

@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthRecord extends BaseModel implements Parcelable{

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


    public StrengthRecord() {
    }

    protected StrengthRecord(Parcel in) {
        id = in.readLong();
        date = in.readString();
        tag = in.readString();
        note = in.readString();
        strengthItemsJson = in.readString();
    }

    public static final Creator<StrengthRecord> CREATOR = new Creator<StrengthRecord>() {
        @Override
        public StrengthRecord createFromParcel(Parcel in) {
            return new StrengthRecord(in);
        }

        @Override
        public StrengthRecord[] newArray(int size) {
            return new StrengthRecord[size];
        }
    };

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

    @Override
    public String toString() {
        return "StrengthRecord{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(date);
        dest.writeString(tag);
        dest.writeString(note);
        dest.writeString(strengthItemsJson);
    }
}
