package com.example.myapplication.AdapterManager;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbManager.khachHangDao;
import com.example.myapplication.InterfaceManager.sendKhachHang;
import com.example.myapplication.ObjectManager.khachHangObj;
import com.example.myapplication.R;
import com.example.myapplication.khachHang;

import java.util.List;

public class khachHangAdapter  extends RecyclerView.Adapter<khachHangAdapter.khachHangViewHolder>
                                implements Filterable {
    private Context mContext;
    private List<khachHangObj> mList;
    private List<khachHangObj> mListOld;
    private sendKhachHang mListener;
    private AlertDialog dialog;
    private khachHangDao dao;

    public khachHangAdapter(Context mContext, sendKhachHang mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public void setmList(List<khachHangObj> mList){
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
        khachHangObj items = mList.get(position);
        if (items == null){
            return;
        }
       holder.item_khach_hang_tv_ten.setText(items.getTenKh());
        holder.item_khach_hang_tv_sdt.setText(items.getSoDienThoai());

        holder.item_khach_hang_tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogUpdateKhachHang(mList.get(position));
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
    public final class khachHangViewHolder extends RecyclerView.ViewHolder {
        TextView item_khach_hang_tv_ten , item_khach_hang_tv_sdt,item_khach_hang_tv_update;
        public khachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            item_khach_hang_tv_ten = itemView.findViewById(R.id.item_khach_hang_tv_ten);
            item_khach_hang_tv_sdt = itemView.findViewById(R.id.item_khach_hang_tv_sdt);
            item_khach_hang_tv_update = itemView.findViewById(R.id.item_khach_hang_tv_update);
        }
    }
    private void ShowDialogUpdateKhachHang(khachHangObj khachHangObj){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_update_khach_hang,null);
        EditText edtCMT = view.findViewById(R.id.dialog_update_khach_hang_edittext_cmt);
        EditText edtTen = view.findViewById(R.id.dialog_update_khach_hang_textEdit_nameKhach);
        EditText edtNgaySinh = view.findViewById(R.id.dialog_update_khach_hang_textEdit_ngaySinh);
        EditText edtSDT = view.findViewById(R.id.dialog_update_khach_hang_textEdit_soDt);
        Button btnUpdate = view.findViewById(R.id.dialog_update_nhan_vien_btnUpdateKhachHang);
        ImageView imageView = view.findViewById(R.id.dialog_khach_hang_vien_img_back);
        builder.setView(view);

        edtCMT.setText(khachHangObj.getSoCMT());
        edtTen.setText(khachHangObj.getTenKh());
        edtNgaySinh.setText(khachHangObj.getNgaySinh());
        edtSDT.setText(khachHangObj.getSoDienThoai());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog = builder.create();
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
