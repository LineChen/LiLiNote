package com.beiing.lilinote.bean;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：一条锻炼项目
 * </br>
 */
@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthItem extends BaseModel {

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
}
