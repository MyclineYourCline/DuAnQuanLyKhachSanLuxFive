package com.example.myapplication.AdapterManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbManager.dichVuDao;
import com.example.myapplication.ObjectManager.chiTietDichVuOBJ;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.R;
import com.example.myapplication.chiTietDichVu;

import java.util.List;

public class itemRcy_dichVu_chiTietPhieuDat extends RecyclerView.Adapter<itemRcy_dichVu_chiTietPhieuDat.ViewHolder> {
    private Context context;
    private List<chiTietDichVuOBJ> mList;
    private dichVuDao mDichVuDao;

    public itemRcy_dichVu_chiTietPhieuDat(Context context) {
        this.context = context;
        mDichVuDao = new dichVuDao(context);
    }
    public void setmList(List<chiTietDichVuOBJ> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_chi_tiet_phieu_dat,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        chiTietDichVuOBJ items = mList.get(position);
        if (items == null){
            return;
        }
        dichVuObj mDichVuObj = mDichVuDao.getByMaDV(items.getMaDichVu());
        holder.tenDV.setText(mDichVuObj.getTenDichVu());
        holder.giaDV.setText(items.getGiaTien());
        holder.soLuong.setText(items.getSoLuong());
    }

    @Override
    public int getItemCount() {
        if (mList.size() != 0){
            return mList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tenDV, soLuong,giaDV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenDV = itemView.findViewById(R.id.item_list_view_chi_tiet_phieu_dat_tenDV);
            soLuong = itemView.findViewById(R.id.item_list_view_chi_tiet_phieu_dat_sl);
            giaDV = itemView.findViewById(R.id.item_list_view_chi_tiet_phieu_dat_giaTien);
        }
    }
}
