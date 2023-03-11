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
import com.example.myapplication.ObjectManager.datPhong;
import com.example.myapplication.R;

import java.util.List;

public class datPhongAdapter extends RecyclerView.Adapter<datPhongAdapter.datPhongViewHolder>
                              implements Filterable {
    private Context mContext;
    private List<datPhong> mList;
    private List<datPhong> mListOld;
    private sendData mListener;
    public datPhongAdapter(Context mContext, sendData mListener) {
        this.mContext = mContext;
        this.mListener = mListener;

    }
    public void setmList(List<datPhong> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public datPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dat_phong
        ,parent,false);
        return new datPhongViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull datPhongViewHolder holder, int position) {
        datPhong items = mList.get(position);
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



    public final class datPhongViewHolder extends RecyclerView.ViewHolder{
            // todo....
        public datPhongViewHolder(@NonNull View itemView) {
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
