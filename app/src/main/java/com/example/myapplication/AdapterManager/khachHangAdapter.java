package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.InterfaceManager.sendKhachHang;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.R;

import java.util.List;

public class khachHangAdapter  extends RecyclerView.Adapter<khachHangAdapter.khachHangViewHolder>
                                implements Filterable {
    private Context mContext;
    private List<khachHangObj> mList;
    private List<khachHangObj> mListOld;
    private sendKhachHang mListener;

    public khachHangAdapter(Context mContext, sendKhachHang mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<khachHangObj> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public khachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khach_hang
        ,parent,false);
        return new khachHangViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull khachHangViewHolder holder, int position) {
        khachHangObj items = mList.get(position);
        if (items == null){
            return;
        }
       holder.item_khach_hang_tv_ten.setText(items.getTenKh());
        holder.item_khach_hang_tv_sdt.setText(items.getSoDienThoai());

    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }
    public final class khachHangViewHolder extends RecyclerView.ViewHolder {
        TextView item_khach_hang_tv_ten , item_khach_hang_tv_sdt;
        public khachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            item_khach_hang_tv_ten = itemView.findViewById(R.id.item_khach_hang_tv_ten);
            item_khach_hang_tv_sdt = itemView.findViewById(R.id.item_khach_hang_tv_sdt);
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
