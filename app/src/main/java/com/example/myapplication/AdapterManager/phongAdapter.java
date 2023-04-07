package com.example.myapplication.AdapterManager;

import static android.util.Log.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.hoaDonDao;
import com.example.myapplication.DbManager.loaiPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class phongAdapter extends RecyclerView.Adapter<phongAdapter.phongViewHolder>
                implements Filterable {
    private Context mContext;
    private loaiPhongDao loaiPhongDao;
    private List<phongObj> mList;
    private List<phongObj> mListOld;
    private sendPhong mListener;
    private datPhongDao mDatPhongDao;
    private datPhongObj mDatPhongObj;
    private phongDao mPhongDao;
    public phongAdapter(Context mContext, sendPhong mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        loaiPhongDao = new loaiPhongDao(mContext);
        mDatPhongDao = new datPhongDao(mContext);
        mPhongDao = new phongDao(mContext);
    }
    public void setmList(List<phongObj> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public phongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong
        ,parent,false);
        return new phongViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull phongViewHolder holder, int position) {
        phongObj items = mList.get(position);
        if (items == null){
            return;
        }
        if (items.getTrangThai().toLowerCase().equals("phòng trống")){
            holder.trangThai.setImageResource(R.drawable.phong_trong);
        }
        else if(items.getTrangThai().toLowerCase().equals("đang dùng")){
            mDatPhongObj = mDatPhongDao.getByMaPhong(items.getMaPhong());
            String timeCheck = mDatPhongObj.getNgayRa()+" "+mDatPhongObj.getGioRa();
            try {
                if (kiemTraTinhTrang(timeCheck)){
                    holder.trangThai.setImageResource(R.drawable.phong_dang_dung);
                    d("ca" + "chung", "onBindViewHolder chua qua han: "+kiemTraTinhTrang(timeCheck));
                    items.setTrangThai("Quá hạn");
                    mPhongDao.updatePhong(items);
                }
                else{
                    holder.trangThai.setImageResource(R.drawable.phong_qua_han);
                    d("ca" + "chung", "onBindViewHolder: qua han"+kiemTraTinhTrang(timeCheck));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            holder.trangThai.setImageResource(R.drawable.phong_qua_han);
        }
        //
        loaiPhongObj loaiPhongObj =  loaiPhongDao.getByMaLoaiPhong(items.getMaLoai());

        if (loaiPhongObj.getTenLoaiPhong().toLowerCase().equals("phòng đơn")){
            holder.anhPhong.setImageResource(R.drawable.bed_single);
        }
        else if (loaiPhongObj.getTenLoaiPhong().toLowerCase().equals("phòng đôi")){
            holder.anhPhong.setImageResource(R.drawable.bed_double);
        }
        else{
            holder.anhPhong.setImageResource(R.drawable.phong_icon);
        }
        holder.tenPhong.setText(items.getTenPhong());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendPhong(items);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public final class phongViewHolder extends RecyclerView.ViewHolder{
        ImageView anhPhong;
        CircleImageView trangThai;
        TextView tenPhong;
        public phongViewHolder(@NonNull View itemView) {
            super(itemView);
            anhPhong = itemView.findViewById(R.id.item_phong_anhPhong);
            tenPhong = itemView.findViewById(R.id.item_phong_tenPhong);
            trangThai = itemView.findViewById(R.id.item_phong_trangThai);
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
    private boolean kiemTraTinhTrang(String thoiGianVe) throws ParseException {
        Date currentDate = new Date();
        LocalDateTime currentDateTime = LocalDateTime.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateToCompare = dateFormat.parse(thoiGianVe);
        int compareResult = currentDate.compareTo(dateToCompare);
        if (compareResult == 0){
                return true;
        }
        else if (compareResult < 0){
                return true;
        }
        else{
            return false;
        }
    }
}
