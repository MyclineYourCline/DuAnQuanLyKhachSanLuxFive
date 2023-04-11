package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendDatPhong;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.R;

import java.util.List;

public class datPhongAdapter extends RecyclerView.Adapter<datPhongAdapter.datPhongViewHolder>
                              implements Filterable {
    private Context mContext;
    private List<datPhongObj> mList;
    private List<datPhongObj> mListOld;
    private sendDatPhong mListener;
    private khachHangObj  mKhachHangObj;
    private khachHangDao mKhachHangDao;
    private phongDao mPhongDao;
    private phongObj mPhongObj;
    public datPhongAdapter(Context mContext, sendDatPhong mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        mKhachHangDao = new khachHangDao(mContext);
        mPhongDao = new phongDao(mContext);

    }
    public void setmList(List<datPhongObj> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public datPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_dat
        ,parent,false);
        return new datPhongViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull datPhongViewHolder holder, int position) {
        datPhongObj items = mList.get(position);
        if (items == null){
            return;
        }
        mKhachHangObj = mKhachHangDao.getByMaKh(items.getMaKh());
        mPhongObj = mPhongDao.getByMaPhong(items.getMaPhong());
        holder.tenNguoiDat.setText("Họ tên: "+mKhachHangObj.getTenKh());
        holder.tenPhong.setText("Phòng: "+mPhongObj.getTenPhong());
        holder.ngayDay.setText("Ngày đặt: "+items.getCheckIn());
        holder.gioDat.setText("Giờ đặt: "+items.getGioVao());
        holder.btn_taoPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendDatPhong(items);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }



    public final class datPhongViewHolder extends RecyclerView.ViewHolder{
           TextView tenNguoiDat, tenPhong, ngayDay, gioDat;
           Button btn_taoPhieu;
        public datPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tenNguoiDat = itemView.findViewById(R.id.item_phieu_dat_tenNguoiDat);
            tenPhong = itemView.findViewById(R.id.item_phieu_dat_phongDat);
            ngayDay = itemView.findViewById(R.id.item_phieu_dat_ngayDat);
            gioDat = itemView.findViewById(R.id.item_phieu_dat_gioDat);
            btn_taoPhieu = itemView.findViewById(R.id.item_phieu_dat_btn);
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
