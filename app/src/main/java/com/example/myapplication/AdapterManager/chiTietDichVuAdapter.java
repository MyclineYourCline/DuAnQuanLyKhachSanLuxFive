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
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.R;

import java.util.List;

public class chiTietDichVuAdapter extends
                            RecyclerView.Adapter<chiTietDichVuAdapter.chiTietDichVuViewHolder>
                            implements Filterable {
    private Context mContext;
    private List<chiTietDichVuOBJ> mList;
    private List<chiTietDichVuOBJ> mListOld;
    private sendData listener;

    public chiTietDichVuAdapter(Context mContext, sendData listener) {
        this.mContext = mContext;
        this.listener = listener;
    }
    public void setmList(List<chiTietDichVuOBJ> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public chiTietDichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_dich_vu
                        ,parent,false);
        return new chiTietDichVuViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull chiTietDichVuViewHolder holder, int position) {
        chiTietDichVuOBJ items = mList.get(position);
        if (items== null){
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
    public final class chiTietDichVuViewHolder extends RecyclerView.ViewHolder{
        // todo...
        public chiTietDichVuViewHolder(@NonNull View itemView) {
            super(itemView);
            // todo...
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
