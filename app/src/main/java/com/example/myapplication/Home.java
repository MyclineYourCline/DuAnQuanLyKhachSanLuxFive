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
import android.view.View;

import com.example.myapplication.AdapterManager.manHinhChinhAdapter;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.ObjectManager.manHinhChinhObj;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private manHinhChinhAdapter adapter;
    private Intent mIntent;

    private nhanVienDao nvDao;

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
            public void sendDada(manHinhChinhObj items) {
                chuyenManHinh(items);
            }

            private void chuyenManHinh(manHinhChinhObj items) {
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
                    case "Quản lý phòng":
                        mIntent = new Intent(Home.this, quanLyPhong.class);
                        startActivity(mIntent);
                        break;

                }
            }
        });

        adapter.setmList(getmList());
        mRecyclerView.setAdapter(adapter);
        // click vào item trong navigation
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_taiKhoanCuaBan:
                        intent = new Intent(Home.this, nhanVien.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("maNhanVien" , items.getMaNhanVien());
//                        intent.putExtra("toUpdate" , bundle);

                        startActivity(intent);
                        break;
                    case R.id.menu_nav_dangXuat:
                        intent = new Intent(Home.this, manHinhDangNhap.class);
                        startActivity(intent);
                        finish();
                        break;



                }
                    return true;
            }
        });

    }
    // chung set giá trị cho list
    private List<manHinhChinhObj> getmList() {
        List<manHinhChinhObj> list = new ArrayList<>();
        list.add(new manHinhChinhObj(R.drawable.tang_icon,"Quản lý Tầng"));
        list.add(new manHinhChinhObj(R.drawable.phong_icon,"Quản lý phòng"));
        list.add(new manHinhChinhObj(R.drawable.loai_phong_icon,"Loại phòng"));
        list.add(new manHinhChinhObj(R.drawable.user,"Khách hàng"));
        list.add(new manHinhChinhObj(R.drawable.dich_vu_icon,"Dịch vụ"));
        list.add(new manHinhChinhObj(R.drawable.hoa_don_icon,"Hóa đơn"));
        list.add(new manHinhChinhObj(R.drawable.quan_ly_nhanvien_icon,"Nhân viên"));
        list.add(new manHinhChinhObj(R.drawable.thongke,"Doanh thu"));
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