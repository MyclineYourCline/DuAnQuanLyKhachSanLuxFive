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
import com.example.myapplication.ObjectManager.hoaDon;
import com.example.myapplication.R;

import java.util.List;

public class hoaDonAdapter extends RecyclerView.Adapter<hoaDonAdapter.hoaDonViewHolder>
                            implements Filterable {
    private Context mContext;
    private List<hoaDon> mList;
    private List<hoaDon> mListOld;
    private sendData mListenr;

    public hoaDonAdapter(Context mContext, sendData mListenr) {
        this.mContext = mContext;
        this.mListenr = mListenr;
    }
    public void setmList( List<hoaDon> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public hoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoan_don
                ,parent,false);
        return new hoaDonViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull hoaDonViewHolder holder, int position) {
        hoaDon items = mList.get(position);
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

    public final class hoaDonViewHolder extends RecyclerView.ViewHolder{

        public hoaDonViewHolder(@NonNull View itemView) {
            super(itemView);

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
