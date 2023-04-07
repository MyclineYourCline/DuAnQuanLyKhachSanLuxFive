package com.example.myapplication.AdapterManager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendHoaDon;
import com.example.myapplication.ObjectManager.datPhongObj;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class hoaDonAdapter extends RecyclerView.Adapter<hoaDonAdapter.hoaDonViewHolder>
        implements Filterable {
    private Context mContext;
    private List<hoaDonObj> mList;
    private List<hoaDonObj> mListOld;
    private sendHoaDon mListenr;
    private datPhongDao mDatPhongDao;
    private datPhongObj mDatPhongObj;
    private phongDao mPhongDao;
    private phongObj mPhongObj;

    public hoaDonAdapter(Context mContext, sendHoaDon mListenr) {
        this.mContext = mContext;
        this.mListenr = mListenr;
        mDatPhongDao = new datPhongDao(mContext);
        mPhongDao = new phongDao(mContext);
    }

    public void setmList(List<hoaDonObj> mList) {
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public hoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoan_don
                , parent, false);
        return new hoaDonViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull hoaDonViewHolder holder, int position) {
        hoaDonObj items = mList.get(position);
        if (items == null) {
            return;
        }
        mDatPhongObj = mDatPhongDao.getBymaDatPhong(items.getMaDatPhong());
        mPhongObj = mPhongDao.getByMaPhong(mDatPhongObj.getMaPhong());

        // gán view cho từng phần tử
        holder.item_hoaDon_tvNamePhong.setText(mPhongObj.getTenPhong());
        holder.item_hoaDon_tvNgayThang.setText(items.getNgayThang());
        holder.item_hoaDon_tvTongTien.setText(items.getTongTien() + " VNĐ");
        //                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        holder.item_hoaDon_btn_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenr.sendHoaDon(items);
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

    public final class hoaDonViewHolder extends RecyclerView.ViewHolder {

        TextView item_hoaDon_tvNamePhong, item_hoaDon_tvNgayThang, item_hoaDon_tvTongTien;
        Button item_hoaDon_btn_chitiet, item_hoaDon_btn_thanhToan;

        public hoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            item_hoaDon_tvNamePhong = itemView.findViewById(R.id.item_hoaDon_tvNamePhong);
            item_hoaDon_tvNgayThang = itemView.findViewById(R.id.item_hoaDon_tvNgayThang);
            item_hoaDon_tvTongTien = itemView.findViewById(R.id.item_hoaDon_tvTongTien);
            item_hoaDon_btn_chitiet = itemView.findViewById(R.id.item_hoaDon_btn_chitiet);
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
