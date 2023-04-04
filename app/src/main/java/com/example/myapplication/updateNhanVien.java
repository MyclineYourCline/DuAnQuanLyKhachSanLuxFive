package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.DbManager.nhanVienDao;
import com.example.myapplication.ObjectManager.nhanVienObj;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class updateNhanVien extends AppCompatActivity {

    private static final int STORAGE_PERMISSION = 23;
    nhanVienDao nvDao;
    Button sua_nhanvien;
    String pattern = "(\\+84|03)\\d{8}";
    ImageView img_back , img_avata;
    TextInputLayout update_nhan_vien_textInput_manv , update_nhan_vien_textInput_sdt , update_nhan_vien_textInput_matkhau, update_nhan_vien_textInput_rematKhau;
    TextInputEditText dialog_update_nhan_vien_Edt_manv, dialog_update_nhan_vien_Edt_sdt,dialog_update_nhan_vien_Edt_matkhau,dialog_update_nhan_vien_Edt_matkhauMoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhan_vien);
        getSupportActionBar().setTitle("sửa thông tin nhân viên");
        initUi();
       Bundle bundle = getIntent().getBundleExtra("toUpdate");
      String ma = bundle.getString("maNhanVien" , "");
      nvDao = new nhanVienDao(updateNhanVien.this);
      nhanVienObj obj = nvDao.getByMaNhanVien(ma);
        if (checkStoragePermission()){
            Glide.with(updateNhanVien.this).load(obj.getAnhNhanVien()).into(img_avata);
            dialog_update_nhan_vien_Edt_manv.setText(obj.getMaNhanVien());
            dialog_update_nhan_vien_Edt_sdt.setText(obj.getSoDienThoai());
            dialog_update_nhan_vien_Edt_matkhau.setText(obj.getMatKhau());

        }

        sua_nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!dialog_update_nhan_vien_Edt_manv.getText().toString().isEmpty() && !dialog_update_nhan_vien_Edt_sdt.getText().toString().isEmpty()
               && !dialog_update_nhan_vien_Edt_matkhau.getText().toString().isEmpty() && !dialog_update_nhan_vien_Edt_matkhauMoi.getText().toString().isEmpty() ){
                   if (dialog_update_nhan_vien_Edt_sdt.getText().toString().matches(pattern)){
                       update_nhan_vien_textInput_sdt.setError("");
//                            if (!nvDao.CheckByMaNhanVien(dialog_update_nhan_vien_Edt_manv.getText().toString() , dialog_update_nhan_vien_Edt_matkhauMoi.getText().toString())){
                                nhanVienObj obj1 = new nhanVienObj();
                                obj1.setAnhNhanVien(obj.getAnhNhanVien());
                                obj1.setMaNhanVien(obj.getMaNhanVien());
                                obj1.setTenNhanVien(obj.getTenNhanVien());
                                obj1.setSoDienThoai(dialog_update_nhan_vien_Edt_sdt.getText().toString());
                                obj1.setMatKhau(dialog_update_nhan_vien_Edt_matkhauMoi.getText().toString());
                                if (nvDao.updateNhanVien(obj1) > 0){
                                    Toast.makeText(updateNhanVien.this, "thay đổi thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

//                            }else {
//                                update_nhan_vien_textInput_manv.setError("đã có mã nhân viên này !");
//                                update_nhan_vien_textInput_rematKhau.setError("đã có mã nhân viên này !");
//                                return;
//
//                            }
                   }else {
                       update_nhan_vien_textInput_sdt.setError("không phải số điện thoại");
                   }
               }else {
                   update_nhan_vien_textInput_manv.setError(dialog_update_nhan_vien_Edt_manv.getText().toString().isEmpty() ? "nhập mã " : "");
                   update_nhan_vien_textInput_sdt.setError(dialog_update_nhan_vien_Edt_sdt.getText().toString().isEmpty() ? "nhập sdt " : "");
                   update_nhan_vien_textInput_matkhau.setError(dialog_update_nhan_vien_Edt_matkhau.getText().toString().isEmpty() ? "nhập mật khẩu " : "");
                   update_nhan_vien_textInput_rematKhau.setError(dialog_update_nhan_vien_Edt_matkhauMoi.getText().toString().isEmpty() ? "nhập mật khẩu mới " : "");
               }



            }
        });









        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    void initUi(){
         img_back = findViewById(R.id.dialog_update_nhan_vien_img_back);
         img_avata = findViewById(R.id.dialog_update_nhan_vien_avata);


        // text input
         update_nhan_vien_textInput_manv = findViewById(R.id.dialog_update_nhan_vien_textInput_manv);
         update_nhan_vien_textInput_sdt =findViewById(R.id.dialog_update_nhan_vien_textInput_sdt);
         update_nhan_vien_textInput_matkhau = findViewById(R.id.dialog_update_nhan_vien_textInput_matkhau);
         update_nhan_vien_textInput_rematKhau =findViewById(R.id.dialog_update_nhan_vien_textInput_matkhauMoi);
        // edit text
         dialog_update_nhan_vien_Edt_manv = findViewById(R.id.dialog_update_nhan_vien_Edt_manv);
         dialog_update_nhan_vien_Edt_sdt = findViewById(R.id.dialog_update_nhan_vien_Edt_sdt);
         dialog_update_nhan_vien_Edt_matkhau =findViewById(R.id.dialog_update_nhan_vien_Edt_matkhau);
         dialog_update_nhan_vien_Edt_matkhauMoi = findViewById(R.id.dialog_update_nhan_vien_Edt_matkhauMoi);
         sua_nhanvien = findViewById(R.id.sua_nhanvien_dialog);


    }




    public boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Quyền chưa được cấp, yêu cầu người dùng cấp quyền
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            return false;
        } else {
            // Quyền đã được cấp
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode  == STORAGE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){

            }
        }else {
            Toast.makeText(updateNhanVien.this, "chưa cấp quyền thư viện", Toast.LENGTH_SHORT).show();
        }
    }
}