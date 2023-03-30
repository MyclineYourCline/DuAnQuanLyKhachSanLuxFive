package com.example.myapplication.AdapterManager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendLoaiPhong;
import com.example.myapplication.ObjectManager.loaiPhongObj;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

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
        //gán view cho từng phần tử
        holder.edtMaLoaiPhong.setText(items.getMaLoai());
        holder.edtTenLoaiPhong.setText(items.getTenLoaiPhong());

        //onclick Sửa hiện dialog
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuaLoaiPhongDialog(Gravity.CENTER);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList!= null){
            return mList.size();
        }
        return 0;
    }
    public final class loaiPhongViewHolder extends RecyclerView.ViewHolder {
        //khai báo các phần tử
        TextInputEditText edtMaLoaiPhong,edtTenLoaiPhong;
        Button btnSua;
        public loaiPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            //gán view cho từng phần tử
            edtMaLoaiPhong = itemView.findViewById(R.id.edtMaLoaiPhong);
            edtTenLoaiPhong = itemView.findViewById(R.id.edtTenLoaiPhong);
            btnSua = itemView.findViewById(R.id.btnSua);
        }
    }

    private void openSuaLoaiPhongDialog(int gravity){
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_loai_phong);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity =gravity;
        window.setAttributes(windowAttribute);

        //Xử lý click ra ngoài dialog
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        Button btnHuy =dialog.findViewById(R.id.btnHuy);
        Button btnSua =dialog.findViewById(R.id.btnSua);

        //Xử lý nút trong Dialog
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiPhongObj loaiPhong = new loaiPhongObj();
            }
        });

        dialog.show();
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
