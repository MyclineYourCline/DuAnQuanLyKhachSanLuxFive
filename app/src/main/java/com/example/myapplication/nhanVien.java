package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import com.example.myapplication.AdapterManager.nhanVienAdapter;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;





import java.util.List;




public class nhanVien extends AppCompatActivity {


    RecyclerView recycleView_nhanvien_activity;
    FloatingActionButton floating_btn_nhanvien_activity;

    List<nhanVienObj> listNhanVien;
    nhanVienDao dao ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        getSupportActionBar().setTitle("Quản lý Nhân viên");
        initUi();

        loadData();







        floating_btn_nhanvien_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nhanVien.this , add_Nhanvien.class);
                startActivity(intent);

            }
        });

    }

    public void loadData() {

        dao = new nhanVienDao(getApplicationContext());
        listNhanVien = dao.getAll();
        nhanVienAdapter adapter = new nhanVienAdapter(nhanVien.this ,listNhanVien);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(nhanVien.this);
        recycleView_nhanvien_activity.setLayoutManager(linearLayoutManager);
        recycleView_nhanvien_activity.setAdapter(adapter);
    }


    private void initUi() {
        recycleView_nhanvien_activity = findViewById(R.id.recycleView_nhanvien_activity);
        floating_btn_nhanvien_activity = findViewById(R.id.floating_btn_nhanvien_activity);

    }


//    private void checkPermission ( ){
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(nhanVien.this, "Permission Granted"
//                        , Toast.LENGTH_SHORT).show();
////                TedBottomSheetDialogFragment.OnImageSelectedListener listener
////                        = new TedBottomSheetDialogFragment.OnImageSelectedListener() {
////                    @Override
////                    public void onImageSelected(Uri uri) {
////                        try {
////                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
////                            imageView.setImageBitmap(bitmap);
////                        } catch (IOException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////                };
//
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(nhanVien.this, "Permission Denied\n"
//                        + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            };
//        TedPermission.create()
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service\n" +
//                        "\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(android.Manifest.permission.CAMERA
//                        , android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}