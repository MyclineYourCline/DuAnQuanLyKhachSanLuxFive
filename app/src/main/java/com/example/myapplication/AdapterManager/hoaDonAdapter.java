package com.example.myapplication.AdapterManager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.InterfaceManager.sendData;
import com.example.myapplication.ObjectManager.dichVuObj;
import com.example.myapplication.ObjectManager.hoaDonObj;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class hoaDonAdapter extends RecyclerView.Adapter<hoaDonAdapter.hoaDonViewHolder>
        implements Filterable {
    private Context mContext;
    private List<hoaDonObj> mList;
    private List<hoaDonObj> mListOld;
    recycle_chitietHoadon_Adapter adapter;
    private sendData mListenr;

    public hoaDonAdapter(Context mContext, List<hoaDonObj> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public hoaDonAdapter(Context mContext, sendData mListenr) {
        this.mContext = mContext;
        this.mListenr = mListenr;
    }

    public void setmList(List<hoaDonObj> mList) {
        this.mList = mList;
        this.mListOld = this.mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public hoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoan_don
                , parent, false);
        return new hoaDonViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull hoaDonViewHolder holder, int position) {
        hoaDonObj items = mList.get(position);
        if (items == null) {
            return;
        }
        // gán view cho từng phần tử
        holder.item_hoaDon_tvNamePhong.setText(items.getMaHoaDon());
        holder.item_hoaDon_tvNgayThang.setText(items.getNgayThang());
        holder.item_hoaDon_tvTongTien.setText(items.getTongTien() + " VNĐ");

        // ấn vào nút chi tiết hiện ra dialog chi tiết
        holder.item_hoaDon_btn_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_chitiet_hoa_don);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                //ánh xạ view dialog  chi tiết//



                ImageView dialog_chitiet_hoaDon_OutDialog = dialog.findViewById(R.id.dialog_chitiet_hoaDon_OutDialog);


                    RecyclerView dialog_chitiet_hoaDon_recyclle_dichVu = dialog.findViewById(R.id.dialog_chitiet_hoaDon_recyclle_dichVu);
                    ArrayList<dichVuObj> arrayList2 = getData();
                adapter = new recycle_chitietHoadon_Adapter(arrayList2);
                LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
                dialog_chitiet_hoaDon_recyclle_dichVu.setLayoutManager(layoutManager);
                dialog_chitiet_hoaDon_recyclle_dichVu.setAdapter(adapter);
                // out dialog



                dialog_chitiet_hoaDon_OutDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



                dialog.show();
            }
        });

        /// ấn vào nút thanh toán hiện ra dialog thanh toán
        holder.item_hoaDon_btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_update_hoa_don);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                // ánh xạ view dialog thanh toán
                ImageView dialog_update_hoaDon_OutDialog = dialog.findViewById(R.id.dialog_update_hoaDon_OutDialog);
                Button btn_hoa_don_thanhToan = dialog.findViewById(R.id.btn_hoa_don_thanhToan);

                // nút X trong dialog thanh toán , ấn vào dialog mất
                dialog_update_hoaDon_OutDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


    }

    private ArrayList<dichVuObj> getData() {
        ArrayList<dichVuObj> listD = new ArrayList<>();
        listD.add(new dichVuObj("nước lọc" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc2" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc3" , "50000" , "2"));
        listD.add(new dichVuObj("nước lọc4" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc4" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc4" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc4" , "20000" , "2"));
        listD.add(new dichVuObj("nước lọc4" , "20000" , "2"));


        return listD;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public final class hoaDonViewHolder extends RecyclerView.ViewHolder {

        TextView item_hoaDon_tvNamePhong, item_hoaDon_tvNgayThang, item_hoaDon_tvTongTien;
        Button item_hoaDon_btn_chitiet, item_hoaDon_btn_thanhToan;

        public hoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            item_hoaDon_tvNamePhong = itemView.findViewById(R.id.item_hoaDon_tvNamePhong);
            item_hoaDon_tvNgayThang = itemView.findViewById(R.id.item_hoaDon_tvNgayThang);
            item_hoaDon_tvTongTien = itemView.findViewById(R.id.item_hoaDon_tvTongTien);
            item_hoaDon_btn_thanhToan = itemView.findViewById(R.id.item_hoaDon_btn_thanhToan);
            item_hoaDon_btn_chitiet = itemView.findViewById(R.id.item_hoaDon_btn_chitiet);
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
