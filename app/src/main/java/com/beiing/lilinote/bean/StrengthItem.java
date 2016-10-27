package com.beiing.lilinote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：一条锻炼项目
 * </br>
 */
@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthItem extends BaseModel implements Parcelable{

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String resPath;//示例图片

    @Column
    String name;//名称

    @Column
    int count;//重复次数

    @Column
    int districtTime;//限时(单位：秒)

    boolean isSelect;

    public StrengthItem(){

    }

    protected StrengthItem(Parcel in) {
        id = in.readLong();
        resPath = in.readString();
        name = in.readString();
        count = in.readInt();
        districtTime = in.readInt();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<StrengthItem> CREATOR = new Creator<StrengthItem>() {
        @Override
        public StrengthItem createFromParcel(Parcel in) {
            return new StrengthItem(in);
        }

        @Override
        public StrengthItem[] newArray(int size) {
            return new StrengthItem[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDistrictTime() {
        return districtTime;
    }

    public void setDistrictTime(int districtTime) {
        this.districtTime = districtTime;
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
        dest.writeString(resPath);
        dest.writeString(name);
        dest.writeInt(count);
        dest.writeInt(districtTime);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StrengthItem)) return false;

        StrengthItem that = (StrengthItem) o;

        if (id != that.id) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
