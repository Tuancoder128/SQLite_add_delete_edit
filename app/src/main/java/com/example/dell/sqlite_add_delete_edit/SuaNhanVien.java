package com.example.dell.sqlite_add_delete_edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.sqlite_add_delete_edit.DTO.NhanVienDTO;

/**
 * Created by dell on 2017-08-15.
 */

public class SuaNhanVien extends AppCompatActivity implements View.OnClickListener {
    EditText edtSuanv;
    Button btnQuayve;
    NhanVienDTO nhanViensua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suanhanvien);
        nhanViensua = (NhanVienDTO) getIntent().getSerializableExtra("nhanvien");

        edtSuanv = (EditText) findViewById(R.id.edt_SuaNhanVien);
        edtSuanv.setText(nhanViensua.getTennhanvien().toString());

        btnQuayve = (Button) findViewById(R.id.btn_QuayVe);
        btnQuayve.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        nhanViensua.setTennhanvien(edtSuanv.getText().toString());
        Intent dulieu = new Intent();
        dulieu.putExtra("suanhanvien",nhanViensua);
        setResult(MainActivity.RESULT_CODE_SUA,dulieu);
        finish();


    }
}
