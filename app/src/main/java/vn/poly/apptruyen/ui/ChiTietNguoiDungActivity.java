package vn.poly.apptruyen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import vn.poly.apptruyen.R;
import vn.poly.apptruyen.dao.NguoiDungDao;


public class ChiTietNguoiDungActivity extends AppCompatActivity {

    private EditText edFullName;
    private EditText edPhone;
    private EditText edmatkhau;
    NguoiDungDao nguoiDungDao;
    String username,fullname,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_dung);
        initView();
        setTitle("Chi Tiết Người Dùng");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nguoiDungDao= new NguoiDungDao(ChiTietNguoiDungActivity.this);
        Intent intent = getIntent();
        Bundle b= intent.getExtras();
        fullname = b.getString("FULLNAME");
        phone=b.getString("PHONE");
        username=b.getString("USERNAME");
        password=b.getString("USERNAME");

        edFullName.setText(fullname);
        edPhone.setText(phone);
        edmatkhau.setText(password);
    }

    public void UpdateUser(View view) {
        if (nguoiDungDao.updateInfoNguoiDung(username,edPhone.getText().toString(),
                edFullName.getText().toString(),username.getBytes().toString())>0){
            Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();

            Intent a = new Intent(ChiTietNguoiDungActivity.this,NguoidungActivity.class);
            startActivity(a);
        }

    }

    public void Huy(View view) {
finish();
    }

    private void initView() {
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edmatkhau = (EditText) findViewById(R.id.edPhone);

    }


}
