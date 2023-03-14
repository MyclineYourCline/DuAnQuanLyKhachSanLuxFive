package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendData;
import com.example.myapplication.ObjectManager.tang;
import com.example.myapplication.R;

import java.util.List;

public class tangAdapter extends RecyclerView.Adapter<tangAdapter.tangViewHolder>
        implements Filterable {
    private Context mContext;
    private List<tang> mList;
    private List<tang> mListOld;
    private sendData mListener;

    public tangAdapter(Context mContext, sendData mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<tang> mList){
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public tangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tang
        ,parent,false);
        return new tangViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull tangViewHolder holder, int position) {
        tang items = mList.get(position);
        if (items == null){
            return;
        }
        //todo...........
        holder.mImageView.setImageResource(R.drawable.tang_smal);
        holder.mTextView.setText(items.getTenTang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendTang(items);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }
    public final class tangViewHolder extends RecyclerView.ViewHolder {
        //todo............
        ImageView mImageView;
        TextView mTextView;
        public tangViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo..........
            mImageView = itemView.findViewById(R.id.item_tang_img);
            mTextView = itemView.findViewById(R.id.item_tang_tenTang);
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
