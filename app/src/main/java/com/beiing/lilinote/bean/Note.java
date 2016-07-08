package com.beiing.lilinote.bean;

import com.beiing.lilinote.db.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by chenliu on 2016/7/8.<br/>
 * 描述：
 * </br>
 */
@ModelContainer
@Table(database = AppDataBase.class)
public class Note extends BaseModel{

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String title;

    @Column
    String date;

    @Column
    String content;
}
