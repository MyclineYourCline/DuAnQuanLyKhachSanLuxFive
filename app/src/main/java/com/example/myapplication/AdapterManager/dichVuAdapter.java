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
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.R;

import java.util.List;

public class dichVuAdapter extends RecyclerView.Adapter<dichVuAdapter.dichVuViewHolder>
                            implements Filterable {
    private Context mContext;
    private List<dichVuObj> mList;
    private List<dichVuObj> mListOld;
    private sendData mListener;

    public dichVuAdapter(Context mContext, sendData mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<dichVuObj> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public dichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dich_vu
        ,parent,false);
        return new dichVuViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull dichVuViewHolder holder, int position) {
        dichVuObj items = mList.get(position);
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

    public final class dichVuViewHolder extends RecyclerView.ViewHolder{
        //todo...
        public dichVuViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo....
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