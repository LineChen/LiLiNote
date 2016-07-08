package com.beiing.lilinote.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by chenliu on 2016/7/8.<br/>
 * 描述：
 * </br>
 */

@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {

    public static final String  NAME = "lilinote";

    public static final int VERSION = 1;
}
