package com.example.myapplication;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.myapplication.AdapterManager.nhanVienAdapter;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class nhanVien extends AppCompatActivity {

    private static final int CODE_THUVIEN = 123;
    RecyclerView recycleView_nhanvien_activity;
    FloatingActionButton floating_btn_nhanvien_activity;

    List<nhanVienObj> listNhanVien;
    nhanVienDao dao;


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
                openDialogadd();

            }
        });

    }

    private void loadData() {
        dao = new nhanVienDao(nhanVien.this);
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

    private void openDialogadd() {
        Dialog dialog = new Dialog(nhanVien.this);
        dialog.setContentView(R.layout.dialog_add_nhan_vien);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ImageView dialog_add_nhan_vien_img_back = dialog.findViewById(R.id.dialog_add_nhan_vien_img_back);

        CircleImageView dialog_add_nhan_vien_avata = dialog.findViewById(R.id.dialog_add_nhan_vien_avata);
        // init text input
        TextInputLayout dialog_add_nhan_vien_textInput_manv = dialog.findViewById(R.id.dialog_add_nhan_vien_textInput_manv);
        TextInputLayout dialog_add_nhan_vien_textInput_sdt = dialog.findViewById(R.id.dialog_add_nhan_vien_textInput_sdt);
        TextInputLayout dialog_add_nhan_vien_textInput_matkhau = dialog.findViewById(R.id.dialog_add_nhan_vien_textInput_matkhau);
        TextInputLayout dialog_add_nhan_vien_textInput_matkhauNhaplai = dialog.findViewById(R.id.dialog_add_nhan_vien_textInput_matkhauNhaplai);
        TextInputLayout dialog_add_nhan_vien_textInput_tenNv = dialog.findViewById(R.id.dialog_add_nhan_vien_textInput_tenNv);
        // init text edit text
        TextInputEditText dialog_add_nhan_vien_Edt_manv = dialog.findViewById(R.id.dialog_add_nhan_vien_Edt_manv);
        TextInputEditText dialog_add_nhan_vien_Edt_sdt = dialog.findViewById(R.id.dialog_add_nhan_vien_Edt_sdt);
        TextInputEditText dialog_add_nhan_vien_Edt_tenNv = dialog.findViewById(R.id.dialog_add_nhan_vien_Edt_tenNv);
        TextInputEditText dialog_add_nhan_vien_Edt_matkhau = dialog.findViewById(R.id.dialog_add_nhan_vien_Edt_matkhau);
        TextInputEditText dialog_add_nhan_vien_Edt_matkhauNhaplai = dialog.findViewById(R.id.dialog_add_nhan_vien_Edt_matkhauNhaplai);
        Button dialog_add_nhan_vien_btnThem = dialog.findViewById(R.id.dialog_add_nhan_vien_btnThem);




  // setImg
        dialog_add_nhan_vien_avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });

        dao = new nhanVienDao(dialog.getContext());
// ấn vào nút thêm
        dialog_add_nhan_vien_btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check trống
                dialog_add_nhan_vien_textInput_manv.setError(dialog_add_nhan_vien_Edt_manv.getText().toString().isEmpty() ? "mã nhân viên trống !" : "");
                dialog_add_nhan_vien_textInput_tenNv.setError(dialog_add_nhan_vien_Edt_tenNv.getText().toString().isEmpty() ? "tên nhân viên trống !" : "");
                dialog_add_nhan_vien_textInput_sdt.setError(dialog_add_nhan_vien_Edt_sdt.getText().toString().isEmpty() ? "sdt nhân viên trống !" :
                        (dialog_add_nhan_vien_Edt_sdt.getText().toString().matches("\\\\d{10}"  ) ? "" : "Không phải số điện thoại"));
                dialog_add_nhan_vien_textInput_matkhau.setError(dialog_add_nhan_vien_Edt_matkhau.getText().toString().isEmpty() ? "mật khẩu không đc để trống":"");
                dialog_add_nhan_vien_textInput_matkhauNhaplai.setError(dialog_add_nhan_vien_Edt_matkhauNhaplai.getText().toString().isEmpty() ? "không đc để trống" : "");


                // thêm vào dataase
                    if (dialog_add_nhan_vien_Edt_matkhauNhaplai.getText().toString().equals(dialog_add_nhan_vien_Edt_matkhau.getText().toString())) {
                        if (!dao.CheckByMaNhanVien(dialog_add_nhan_vien_Edt_manv.getText().toString(), dialog_add_nhan_vien_Edt_matkhauNhaplai.getText().toString())) {
                            nhanVienObj nhanvien = new nhanVienObj();
                            nhanvien.setMaNhanVien(dialog_add_nhan_vien_Edt_manv.getText().toString());
                            nhanvien.setTenNhanVien(dialog_add_nhan_vien_Edt_tenNv.getText().toString());
                            nhanvien.setSoDienThoai(dialog_add_nhan_vien_Edt_sdt.getText().toString());
                            int resID = getResources().getIdentifier("user", "drawable", getPackageName());
                            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resID);
                            String path = uri.getPath();
                            nhanvien.setAnhNhanVien(path);
                            nhanvien.setMatKhau(dialog_add_nhan_vien_Edt_matkhauNhaplai.getText().toString());
                            if (dao.insertNhanVien(nhanvien) > 0) {
                                Toast.makeText(nhanVien.this, "thêm nhân viên Thành công", Toast.LENGTH_SHORT).show();
                                loadData();
                                dialog.dismiss();
                            } else {
                                return;
                            }
                        }else {
                            Toast.makeText(nhanVien.this, "tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }




        });


        dialog_add_nhan_vien_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }
    private void checkPermission ( ){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(nhanVien.this, "Permission Granted"
                        , Toast.LENGTH_SHORT).show();
//                TedBottomSheetDialogFragment.OnImageSelectedListener listener
//                        = new TedBottomSheetDialogFragment.OnImageSelectedListener() {
//                    @Override
//                    public void onImageSelected(Uri uri) {
//                        try {
//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                            imageView.setImageBitmap(bitmap);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                };

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(nhanVien.this, "Permission Denied\n"
                        + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

            };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n" +
                        "\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(android.Manifest.permission.CAMERA
                        , android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }
}