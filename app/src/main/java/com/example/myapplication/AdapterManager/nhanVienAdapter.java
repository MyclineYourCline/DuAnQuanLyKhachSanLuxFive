package com.example.myapplication.AdapterManager;

import android.app.Dialog;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.InterfaceManager.sendNhanVien;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.example.myapplication.R;

import com.example.myapplication.nhanVien;
import com.example.myapplication.updateNhanVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class nhanVienAdapter extends RecyclerView.Adapter<nhanVienAdapter.nhanVienViewHolder>
        implements Filterable {
    private Context mContext;
    private List<nhanVienObj> mList;
    private List<nhanVienObj> mListOld;
    private sendNhanVien mListener;

    nhanVien  nhanVien;

    nhanVienDao dao;
    int count = 1;

    public nhanVienAdapter(Context mContext, sendNhanVien mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public nhanVienAdapter(Context mContext, List<nhanVienObj> mList) {
        this.mContext = mContext;
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setmList(List<nhanVienObj> mList) {
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public nhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_vien
                , parent, false);

        return new nhanVienViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull nhanVienViewHolder holder, int position) {
        int pos = position;
        nhanVienObj items = mList.get(pos);
        if (items == null) {
            return;
        }


        Glide.with(mContext).load(items.getAnhNhanVien()).into(holder.item_nhan_vien_avata);


        holder.item_nhan_vien_tv_ten.setText(items.getTenNhanVien());
        holder.item_nhan_vien_tv_sdt.setText(items.getSoDienThoai());

        holder.img_xoa_nhanvien.setVisibility(View.GONE);


//        holder
//                .itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext , updateNhanVien.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("maNhanVien" , items.getMaNhanVien());
//                        intent.putExtra("toUpdate" , bundle);
//                        mContext.startActivity(intent);
//
//
////
////
//                    }});

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                count++;
                if (count % 2 == 0) {
                    holder.img_xoa_nhanvien.setVisibility(View.VISIBLE);
                } else {
                    holder.img_xoa_nhanvien.setVisibility(View.GONE);
                }
                return true;
            }
        });

        holder.img_xoa_nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new nhanVienDao(mContext);
                dao.deleteNhanVien(items.getMaNhanVien());
                mList.remove(items);
                notifyItemRemoved(pos);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public final class nhanVienViewHolder extends RecyclerView.ViewHolder {
        ImageView item_nhan_vien_avata, img_xoa_nhanvien;
        TextView item_nhan_vien_tv_ten, item_nhan_vien_tv_sdt;

        public nhanVienViewHolder(@NonNull View itemView) {
            super(itemView);
            img_xoa_nhanvien = itemView.findViewById(R.id.img_xoa_nhanvien);
            item_nhan_vien_avata = itemView.findViewById(R.id.item_nhan_vien_avata);
            item_nhan_vien_tv_ten = itemView.findViewById(R.id.item_nhan_vien_tv_ten);
            item_nhan_vien_tv_sdt = itemView.findViewById(R.id.item_nhan_vien_tv_sdt);


        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }




}
