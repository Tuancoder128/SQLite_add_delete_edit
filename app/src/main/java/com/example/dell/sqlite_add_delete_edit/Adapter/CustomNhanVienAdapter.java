package com.example.dell.sqlite_add_delete_edit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dell.sqlite_add_delete_edit.DTO.NhanVienDTO;
import com.example.dell.sqlite_add_delete_edit.R;

import java.util.List;

/**
 * Created by dell on 2017-08-05.
 */

public class CustomNhanVienAdapter extends ArrayAdapter<NhanVienDTO> {
    Context context;
    int resource;
    List<NhanVienDTO> objects;

    public CustomNhanVienAdapter(Context context, int resource, List<NhanVienDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(resource,parent,false);

        TextView textView = (TextView) view.findViewById(R.id.tvNhanVien);
        textView.setText(objects.get(position).getTennhanvien().toString());

        return view;
    }
}
