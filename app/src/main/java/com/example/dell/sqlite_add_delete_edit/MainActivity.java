package com.example.dell.sqlite_add_delete_edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.sqlite_add_delete_edit.Adapter.CustomNhanVienAdapter;
import com.example.dell.sqlite_add_delete_edit.DAO.NhanVienDAO;
import com.example.dell.sqlite_add_delete_edit.DTO.NhanVienDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnThem, btnxoa;
    EditText edtTenNhanVien;
    NhanVienDAO nhanVienDAO;
    ListView listNhanVien;
    List<NhanVienDTO> dsNhanVien;
    CustomNhanVienAdapter adapter;


    public static final int REQUEST_CODE_SUA = 1;
    public static final int RESULT_CODE_SUA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = (Button) findViewById(R.id.btnThem);
        btnThem.setOnClickListener(this);
        edtTenNhanVien = (EditText) findViewById(R.id.edtTenNhanVien);
        listNhanVien = (ListView) findViewById(R.id.LvDanhSachNhanVien);
        // gọi ra từ adapter
        dsNhanVien = new ArrayList<NhanVienDTO>();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienDAO.open();
        dsNhanVien = nhanVienDAO.Laydanhsachnhanvien();

        adapter = new CustomNhanVienAdapter(this, R.layout.layout_customnhanvien, dsNhanVien);
        listNhanVien.setAdapter(adapter);
        // sau đó gọi ra phương thức mở bảng nhân viên
        adapter.notifyDataSetChanged();

        btnxoa = (Button) findViewById(R.id.btnXoa);
        btnxoa.setOnClickListener(this);
        registerForContextMenu(listNhanVien);

    }

    @Override
    // tạo ra menu khi ta nhấn vào dòng item
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    // thực thi tác vụ thêm ,sửa ,xóa khi ta click vào Item
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnSua:

                AdapterView.AdapterContextMenuInfo menuInfoSua = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Intent imoManHinhSua = new Intent(MainActivity.this, SuaNhanVien.class);

                NhanVienDTO nhanvienSua = dsNhanVien.get(menuInfoSua.position);
                imoManHinhSua.putExtra("nhanvien", nhanvienSua);

                startActivityForResult(imoManHinhSua, REQUEST_CODE_SUA);
                break;

            case R.id.mnXoa:
                // lấy ra thông tin khi ta click vào Item
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                NhanVienDTO nhanVien = dsNhanVien.get(menuInfo.position);
                nhanVienDAO.XoaDuLieu(nhanVien);
                adapter.remove(nhanVien);
                adapter.notifyDataSetChanged();
                ;
                break;


        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUA) {
            switch (resultCode) {
                case RESULT_CODE_SUA:
                    // NhanVienDTO nhanvienmoi = (NhanVienDTO) data.getSerializableExtra("nhanviensua");
                    NhanVienDTO nhanvienmoi = (NhanVienDTO) data.getSerializableExtra("suanhanvien");
                    nhanVienDAO.Update(nhanvienmoi);

                    dsNhanVien = nhanVienDAO.Laydanhsachnhanvien();
                    adapter = new CustomNhanVienAdapter(this, R.layout.layout_customnhanvien, dsNhanVien);
                    adapter.notifyDataSetChanged();
                    listNhanVien.setAdapter(adapter);
                    ;
                    break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThem:
                // tạo ra 1 biến String để nhận dữ liệu người dùng nhập vào
                String tennhanvien = edtTenNhanVien.getText().toString();
                // gọi ra class DTO
                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                // set Tennhanvien
                nhanVienDTO.setTennhanvien(tennhanvien);
                //add data từ class vào List <>
                dsNhanVien.add(nhanVienDTO);
                boolean kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                if (kiemtra) {
                    Toast.makeText(MainActivity.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }

                break;

            case R.id.btnXoa:
                // gọi ra data của List<> , get vị trí 0 để thao tác xóa
                // xóa của set cứng data
                NhanVienDTO nhanVienDTO1 = dsNhanVien.get(0);
                nhanVienDAO.XoaDuLieu(nhanVienDTO1);
                adapter.remove(nhanVienDTO1);
                adapter.notifyDataSetChanged();
                ;
                break;
        }
    }
}











