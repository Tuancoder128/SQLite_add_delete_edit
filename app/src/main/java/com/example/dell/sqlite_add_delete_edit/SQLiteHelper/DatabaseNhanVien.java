package com.example.dell.sqlite_add_delete_edit.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2017-08-03.
 */

public class DatabaseNhanVien extends SQLiteOpenHelper {
    // để tạo database(SQLite trong Android) ta cần extends SQLiteOpenHelper
    //constructer cần kế thừa 3 hàm : onCreate,onUpgrade và constructer

    public static final String DB_QUANLINHANVIEN = "QuanLiNhanVien";
    public static final int DB_VERSION = 1;
    public static final String TB_NHANVIEN = "NHANVIEN";
    public static final String ID_TBNHANVIEN = "_id";
    public static final String TENNHANVIEN_TBNHANVIEN = "TenNhanVien";

    public DatabaseNhanVien(Context context)
    {
        super(context, DB_QUANLINHANVIEN, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng SQL
        String cautruyvan = "CREATE TABLE "+ TB_NHANVIEN + "( " + ID_TBNHANVIEN + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + TENNHANVIEN_TBNHANVIEN + "TEXT );";
        db.execSQL(cautruyvan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TB_NHANVIEN );
        onCreate(db);
    }
}
