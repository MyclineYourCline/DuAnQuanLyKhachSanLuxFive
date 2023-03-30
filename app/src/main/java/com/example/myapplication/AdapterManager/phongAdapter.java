package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.ObjectManager.phongObj;
import com.example.myapplication.R;

import java.util.List;

public class phongAdapter extends RecyclerView.Adapter<phongAdapter.phongViewHolder>
                implements Filterable {
    private Context mContext;
    private List<phongObj> mList;
    private List<phongObj> mListOld;
    private sendPhong mListener;

    public phongAdapter(Context mContext, sendPhong mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
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
        //todo.....
    }


    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public final class phongViewHolder extends RecyclerView.ViewHolder{
        // todo.......
        public phongViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo........
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
