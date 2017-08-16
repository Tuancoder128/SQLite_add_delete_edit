package com.example.dell.sqlite_add_delete_edit.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.sqlite_add_delete_edit.DTO.NhanVienDTO;
import com.example.dell.sqlite_add_delete_edit.SQLiteHelper.DatabaseNhanVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017-08-03.
 */

public class NhanVienDAO {
    SQLiteDatabase database;
    DatabaseNhanVien databaseNhanVien;
    String[] ColumNhanVien = {databaseNhanVien.ID_TBNHANVIEN, databaseNhanVien.TENNHANVIEN_TBNHANVIEN};

    public NhanVienDAO(Context context) {
        databaseNhanVien = new DatabaseNhanVien(context);

    }

    public void open() {
        database = databaseNhanVien.getWritableDatabase();
    }

    public void close() {
        databaseNhanVien.close();
    }

    public boolean ThemNhanVien(NhanVienDTO nv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseNhanVien.TENNHANVIEN_TBNHANVIEN, nv.getTennhanvien().toString());

        long id_nhanvien = database.insert(DatabaseNhanVien.TB_NHANVIEN, null, contentValues);
        if (id_nhanvien != 0) {
            return true;
        } else {
            return false;
        }


    }

    // gọi ra hàm lấy ds nhân viên , và hàm này không cần tham số truyền vào,return về danh sách nhân viên
    public List<NhanVienDTO> Laydanhsachnhanvien() {
        // gọi List<NhanVienDTO> danhsachnhanvien  để return về danhsachnhanvien
        List<NhanVienDTO> danhsachnhanvien = new ArrayList<NhanVienDTO>();
        // Cursor cursor = database.query(databaseNhanVien.TB_NHANVIEN,ColumNhanVien,null,null,null,null,null);
        String laydata_tutable = "select * from " + databaseNhanVien.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(laydata_tutable, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //lay ra gia tri cua tung truong id va nhan vien
            //int id_nhanvien = cursor.getInt(0);
            int id_nhanvien = cursor.getInt(cursor.getColumnIndex(databaseNhanVien.ID_TBNHANVIEN));
            //String tennhanvien =  cursor.getString(1);
            String tennhanvien = cursor.getString(cursor.getColumnIndex(databaseNhanVien.TENNHANVIEN_TBNHANVIEN));
            //add data vua lay ra duoc vao nhanVienDTO
            //cu moi lan duyet data ta lai new class ra 1 lan
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            //set giá trị mới cho class
            nhanVienDTO.set_id(id_nhanvien);
            nhanVienDTO.setTennhanvien(tennhanvien);
            //add data vua lay ra duoc vao  List<NhanVienDTO>
            danhsachnhanvien.add(nhanVienDTO);
            cursor.moveToNext();
        }
        return danhsachnhanvien;
    }

    public boolean XoaDuLieu(NhanVienDTO nhanvien) {
        // delete * from Nhanvien where id = Nhanvien.getid();
        int kiemtra = database.delete(databaseNhanVien.TB_NHANVIEN, databaseNhanVien.ID_TBNHANVIEN + " = " + nhanvien.get_id(), null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Update( NhanVienDTO nhanvienmoi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseNhanVien.TENNHANVIEN_TBNHANVIEN, nhanvienmoi.getTennhanvien().toString());
        int kt = database.update(databaseNhanVien.TB_NHANVIEN, contentValues, databaseNhanVien.ID_TBNHANVIEN + " = " + nhanvienmoi.get_id(), null);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }


    }
}
