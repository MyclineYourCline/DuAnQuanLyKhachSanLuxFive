package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendLoaiPhong;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.R;

import java.util.List;

public class loaiPhongAdapter extends RecyclerView.Adapter<loaiPhongAdapter.loaiPhongViewHolder>
                                implements Filterable {
    private Context mContext;
    private List<loaiPhongObj> mList;
    private List<loaiPhongObj> mListOld;
    private sendLoaiPhong mListener;

    public loaiPhongAdapter(Context mContext, sendLoaiPhong mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<loaiPhongObj> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public loaiPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_phong
        ,parent,false);
        return new loaiPhongViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull loaiPhongViewHolder holder, int position) {
        loaiPhongObj items = mList.get(position);
        if (items == null){
            return;
        }
        //todo...
    }

    @Override
    public int getItemCount() {
        if (mList!= null){
            return mList.size();
        }
        return 0;
    }
    public final class loaiPhongViewHolder extends RecyclerView.ViewHolder {
        //todo...
        public loaiPhongViewHolder(@NonNull View itemView) {
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
