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
import com.example.myapplication.ObjectManager.nhanVien;
import com.example.myapplication.R;

import java.util.List;

public class nhanVienAdapter extends RecyclerView.Adapter<nhanVienAdapter.nhanVienViewHolder>
                            implements Filterable {
    private Context mContext;
    private List<nhanVien> mList;
    private List<nhanVien> mListOld;
    private sendData mListener;

    public nhanVienAdapter(Context mContext, sendData mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<nhanVien> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public nhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_vien
        ,parent,false);

        return new nhanVienViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull nhanVienViewHolder holder, int position) {
        nhanVien items = mList.get(position);
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
    public final class nhanVienViewHolder extends RecyclerView.ViewHolder{
        // todo...
        public nhanVienViewHolder(@NonNull View itemView) {
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
