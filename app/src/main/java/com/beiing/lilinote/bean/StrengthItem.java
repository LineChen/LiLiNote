package com.beiing.lilinote.bean;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：
 * </br>
 */
public class StrengthItem {

    String resPath;//示例图片

    String name;//名称

    int count;//重复次数

    int districtTime;//限时(单位：秒)

    boolean isSelect;

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
