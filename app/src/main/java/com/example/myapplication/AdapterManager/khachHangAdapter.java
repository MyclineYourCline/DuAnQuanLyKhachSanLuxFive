package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendData;
import com.example.myapplication.ObjectManager.khachHang;
import com.example.myapplication.R;

import java.util.List;

public class khachHangAdapter  extends RecyclerView.Adapter<khachHangAdapter.khachHangViewHolder>
                                implements Filterable {
    private Context mContext;
    private List<khachHang> mList;
    private List<khachHang> mListOld;
    private sendData mListener;

    public khachHangAdapter(Context mContext, sendData mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<khachHang> mList){
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
        khachHang items = mList.get(position);
        if (items == null){
            return;
        }
        //todo...

    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }
    public final class khachHangViewHolder extends RecyclerView.ViewHolder {
        // todo..
        public khachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo...
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
