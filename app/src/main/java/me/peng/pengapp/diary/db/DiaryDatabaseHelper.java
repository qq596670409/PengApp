package me.peng.pengapp.diary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.sqlite.SQLiteDatabase.*;

/**
 * 数据库的帮助类
 * Created by Administrator on 2017/8/7.
 */

public class DiaryDatabaseHelper extends SQLiteOpenHelper{

    private Context mContext;

    private static final String CREATE_DIARY = "create table Diary("
            + "id integer primary key autoincrement, "
            + "date text, "
            + "tag text, "
            + "title text, "
            + "content text)";



    public DiaryDatabaseHelper(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Diary");
        onCreate(db);
    }
}
