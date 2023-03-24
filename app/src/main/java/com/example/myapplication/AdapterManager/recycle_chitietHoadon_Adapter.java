package com.example.myapplication.AdapterManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.R;

import java.util.ArrayList;

public class recycle_chitietHoadon_Adapter extends RecyclerView.Adapter<recycle_chitietHoadon_Adapter.chitiet_ViewHolder>{
        ArrayList<dichVuObj> arrayList;

    public recycle_chitietHoadon_Adapter(ArrayList<dichVuObj> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public chitiet_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_chitiet_hoadon
                ,parent,false);
        return new chitiet_ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull chitiet_ViewHolder holder, int position) {
            dichVuObj items = arrayList.get(position);
        if (items== null){
            return;
        }
        holder.item_recycle_chitiet_hoadon_tvTenDichVu.setText(items.getTenDichVu());
        holder.item_recycle_chitiet_hoadon_SoLuong.setText(items.getSoLuong());
        holder.item_recycle_chitiet_hoadon_tvGia.setText(items.getGiaDichVu());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class  chitiet_ViewHolder extends RecyclerView.ViewHolder{
        TextView item_recycle_chitiet_hoadon_tvTenDichVu ,item_recycle_chitiet_hoadon_SoLuong , item_recycle_chitiet_hoadon_tvGia;
    public chitiet_ViewHolder(@NonNull View itemView) {
        super(itemView);

        item_recycle_chitiet_hoadon_tvTenDichVu = itemView.findViewById(R.id.item_recycle_chitiet_hoadon_tvTenDichVu);
        item_recycle_chitiet_hoadon_SoLuong = itemView.findViewById(R.id.soluong);
        item_recycle_chitiet_hoadon_tvGia = itemView.findViewById(R.id.gia);
    }
}
}
