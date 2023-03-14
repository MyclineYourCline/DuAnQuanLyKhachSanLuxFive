package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.AdapterManager.manHinhChinhAdapter;
import com.example.myapplication.ObjectManager.manHinhChinh;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private manHinhChinhAdapter adapter;
    private Intent mIntent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Lux Five Hotel Manager");
        mRecyclerView = findViewById(R.id.activity_home_recycleView);
        mDrawerLayout = findViewById(R.id.activity_home_drawer);
        mNavigationView = findViewById(R.id.activity_home_navigation);
        //
        adapter = new manHinhChinhAdapter(Home.this, new manHinhChinhAdapter.senData() {
            @Override
            public void sendDada(manHinhChinh items) {
                chuyenManHinh(items);
            }

            private void chuyenManHinh(manHinhChinh items) {
                switch (items.getName().trim()){
                    case "Quản lý Tầng":
                        mIntent = new Intent(Home.this, quanLyTang.class);
                        startActivity(mIntent);
                        break;
                    case "Loại phòng":
                        mIntent = new Intent(Home.this, loaiPhong.class);
                        startActivity(mIntent);
                        break;
                    case "Khách hàng":
                        mIntent = new Intent(Home.this, khachHang.class);
                        startActivity(mIntent);
                        break;
                    case "Dịch vụ":
                        mIntent = new Intent(Home.this, dichVu.class);
                        startActivity(mIntent);
                        break;
                    case "Hóa đơn":
                        mIntent = new Intent(Home.this, hoaDon.class);
                        startActivity(mIntent);
                        break;
                    case "Nhân viên":
                        mIntent = new Intent(Home.this, nhanVien.class);
                        startActivity(mIntent);
                        break;
                    case "Doanh thu":
                        mIntent = new Intent(Home.this, doanhThu.class);
                        startActivity(mIntent);
                        break;
                    case "Trang chủ":
                        //
                        break;
                    case "FeedBack":
                        mIntent = new Intent(Home.this, feedBack.class);
                        startActivity(mIntent);
                        break;
                    case "Quản lý phòng":
                        mIntent = new Intent(Home.this, quanLyPhong.class);
                        startActivity(mIntent);
                        break;

                }
            }
        });

        adapter.setmList(getmList());
        mRecyclerView.setAdapter(adapter);
    }
    // chung set giá trị cho list
    private List<manHinhChinh> getmList() {
        List<manHinhChinh> list = new ArrayList<>();
        list.add(new manHinhChinh(R.drawable.tang_icon,"Quản lý Tầng"));
        list.add(new manHinhChinh(R.drawable.phong_icon,"Quản lý phòng"));
        list.add(new manHinhChinh(R.drawable.loai_phong_icon,"Loại phòng"));
        list.add(new manHinhChinh(R.drawable.user,"Khách hàng"));
        list.add(new manHinhChinh(R.drawable.dich_vu_icon,"Dịch vụ"));
        list.add(new manHinhChinh(R.drawable.hoa_don_icon,"Hóa đơn"));
        list.add(new manHinhChinh(R.drawable.quan_ly_nhanvien_icon,"Nhân viên"));
        list.add(new manHinhChinh(R.drawable.thongke,"Doanh thu"));
        list.add(new manHinhChinh(R.drawable.home,"Trang chủ"));
        list.add(new manHinhChinh(R.drawable.baseline_feedback_24,"FeedBack"));

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home_navigation:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}