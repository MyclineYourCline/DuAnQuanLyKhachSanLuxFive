package com.example.myapplication;

import static android.util.Log.d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.AdapterManager.phongAdapter;
import com.example.myapplication.DbManager.datPhongDao;
import com.example.myapplication.DbManager.phongDao;
import com.example.myapplication.InterfaceManager.sendPhong;
import com.example.myapplication.ObjectManager.phongObj;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class manHinhPhieuDat extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditText_d1, mEditText_d2,mEditText_d3;
    private ImageView mImageView_i1, mImageView_i2,mImageView_i3;
    private phongAdapter adapter;
    private phongDao mPhongDao;
    private datPhongDao mDatPhongDao;
    private RecyclerView mRecyclerView;
    private Button btn_search;
    private String hinhThucThue = "giờ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_phieu_dat);
        getSupportActionBar().setTitle("Phiếu đặt trước");
        mPhongDao = new phongDao(manHinhPhieuDat.this);
        mDatPhongDao = new datPhongDao(manHinhPhieuDat.this);
        unNitIu();
        mEditText_d1.setOnClickListener(this);
        mImageView_i1.setOnClickListener(this);
        mImageView_i3.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        adapter = new phongAdapter(manHinhPhieuDat.this, new sendPhong() {
            @Override
            public void sendPhong(phongObj items) {

            }
        });
        capNhapDuLieu(getPhongObj());
        //
    }
    private void  unNitIu(){
        mEditText_d1 = findViewById(R.id.activity_man_hinh_phieu_dat_txtImage1);
        mImageView_i1 = findViewById(R.id.activity_man_hinh_phieu_dat_image1);
        mImageView_i3 = findViewById(R.id.activity_man_hinh_phieu_dat_image3);
        mRecyclerView = findViewById(R.id.activity_man_hinh_phieu_dat_Rc);
        btn_search = findViewById(R.id.btnSearch);
        mEditText_d3 = findViewById(R.id.activity_man_hinh_phieu_dat_txtImage3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_man_hinh_phieu_dat_image3:
                TimePickerDialog timePickerDialog = new TimePickerDialog
                        (manHinhPhieuDat.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourOfDayString = hourOfDay+"";
                        String minuteString = minute+"";
                        if (hourOfDay <9){
                            hourOfDayString = "0"+hourOfDay;
                        }
                        if (minute <10){
                            minuteString = "0"+minute;
                        }
                        mEditText_d3.setText(hourOfDayString+":"+minuteString+":00");
                    }
                },12,0,true);
                timePickerDialog.show();

                break;
            case R.id.activity_man_hinh_phieu_dat_txtImage1:
                break;
            case R.id.activity_man_hinh_phieu_dat_image1:
                clickImage(mEditText_d1);
                break;
            case R.id.btnSearch:
                if (mEditText_d1.getText().toString().isEmpty() && mEditText_d3.getText().toString().isEmpty()){
                    capNhapDuLieu(getPhongObj());
                    return;
                }

                        List<phongObj> list = mDatPhongDao.truyVanTaoPhieuCho(mEditText_d1.getText().toString().trim(),
                                mEditText_d3.getText().toString().trim());
                        capNhapDuLieu(list);

                break;

        }
    }
    private void clickImage(TextView mTextView){
        DatePickerDialog dialog = new DatePickerDialog(manHinhPhieuDat.this);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthString = (month+1)+"";
                String dayOfMonthString = dayOfMonth+"";
                if (month <10){
                    monthString = "0"+(month+1);
                }
                if (dayOfMonth <10){
                    dayOfMonthString = "0"+dayOfMonth;
                }
                String time = year+"-"+monthString+"-"+dayOfMonthString;

                mTextView.setText(time);
            }
        });
        dialog.show();
    }
    private void capNhapDuLieu(List<phongObj> list){
       adapter.setmList(list);
       mRecyclerView.setAdapter(adapter);
    }
    private List<phongObj> getPhongObj(){
        List<phongObj > list = mPhongDao.getAll();
        return list;
    }
    private LocalTime chuyenDoiSoVeGio(double hour){
        double hours = hour;
        int seconds = (int) (hours * 3600);
        LocalTime time = LocalTime.ofSecondOfDay(seconds);
        return time;
    }
    public String checkTimeOut(){
        String timeIn = mEditText_d3.getText().toString();
        String thoiGianDat = mEditText_d2.getText().toString();
        LocalTime t1 = LocalTime.parse(timeIn, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime timeLC = chuyenDoiSoVeGio(Double.parseDouble(thoiGianDat));
        String getHourString = timeLC.getHour()+"";
        String getMinuteString = timeLC.getMinute()+"";
        if (timeLC.getHour() < 10 ){
            getHourString = "0"+timeLC.getHour();
        }
        if (timeLC.getMinute() < 10){
            getMinuteString = "0"+timeLC.getMinute();
        }
        String Tparse  = getHourString+":"+getMinuteString+":00";
        LocalTime t2 = LocalTime.parse(Tparse, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime sum = t1.plusHours(t2.getHour())
                .plusMinutes(t2.getMinute())
                .plusSeconds(t2.getSecond());
        String formattedTime = sum.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return formattedTime;
    }
    public String checkDayOut (){
        String dateString =  mEditText_d1.getText().toString().trim()+" "+ mEditText_d3.getText().toString().trim() ;
        String thoiGianDat = mEditText_d2.getText().toString().trim();
        LocalTime timeLC = chuyenDoiSoVeGio(Double.parseDouble(thoiGianDat));
        String getHourString = timeLC.getHour()+"";
        String getMinuteString = timeLC.getMinute()+"";
        if (timeLC.getHour() < 10 ){
            getHourString = "0"+timeLC.getHour();
        }
        if (timeLC.getMinute() < 10){
            getMinuteString = "0"+timeLC.getMinute();
        }
        String Tparse  = getHourString+":"+getMinuteString+":00";
        //
        LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalTime time = LocalTime.parse(Tparse, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime newDateTime = dateTime.plusHours(time.getHour()).plusMinutes(time.getMinute()).plusSeconds(time.getSecond());
        String newDateTimeString = newDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Định dạng chuỗi kết quả
        return newDateTimeString;
    }
    private LocalDateTime tinhNgayRa(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timr = mEditText_d1.getText().toString().trim()+" "+mEditText_d3.getText().toString().trim();
        LocalDateTime localTime = LocalDateTime.parse(timr,dateTimeFormatter);
        int chuyendoi [] =  chuyenDoiNgay(Double.parseDouble(mEditText_d2.getText().toString().trim()));
        LocalDateTime result = localTime.plusDays(chuyendoi[0]).plusHours(chuyendoi[1]);
        return  result;
    }
    private int [] chuyenDoiNgay (double soChuyen){
        double daysToAdd = soChuyen;
        int wholeDays = (int) daysToAdd;
        int hoursToAdd = (int) ((daysToAdd - wholeDays) * 24);
        int [] result =   {wholeDays, hoursToAdd};
        return  result;
    }

}