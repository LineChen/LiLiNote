package com.beiing.lilinote.bean;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.List;

/**
 * Created by chenliu on 2016/10/25.<br/>
 * 描述：锻炼项目
 * </br>
 */

@ModelContainer
@Table(database = AppDataBase.class)
public class StrengthRecord {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    String date;//日期

    String tag;//标签

    String note;//备注

    List<StrengthItem> strengthItems;//项目集合
}
