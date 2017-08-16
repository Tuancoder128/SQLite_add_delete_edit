package com.example.dell.sqlite_add_delete_edit.DTO;

import java.io.Serializable;

/**
 * Created by dell on 2017-08-04.
 */
// để put 1 lớp đối tượng ta cần implements về Serializable
public class NhanVienDTO implements Serializable {
    int _id;
    String tennhanvien;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }
}
