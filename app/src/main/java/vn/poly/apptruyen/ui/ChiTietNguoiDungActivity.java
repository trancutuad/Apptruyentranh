package vn.poly.apptruyen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import vn.poly.apptruyen.R;
import vn.poly.apptruyen.dao.NguoiDungDao;


public class ChiTietNguoiDungActivity extends AppCompatActivity {

    private EditText edPassWord;
    NguoiDungDao nguoiDungDao;
    String username,password;
    TextView edUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_dung);
        initView();
        setTitle("Chi Tiết Người Dùng");

        edPassWord = findViewById(R.id.edPassWord);
        edUsername=findViewById(R.id.edUsername);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nguoiDungDao= new NguoiDungDao(ChiTietNguoiDungActivity.this);
        Intent intent = getIntent();
        Bundle b= intent.getExtras();

        username=b.getString("USERNAME");
        password=b.getString("PASSWORD");


        edPassWord.setText(password);
        edUsername.setText("Tài khoản: "+username);
    }

    public void UpdateUser(View view) {
        if (nguoiDungDao.updateInfoNguoiDung(username,edPassWord.getText().toString())>0){
            Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();

            Intent a = new Intent(ChiTietNguoiDungActivity.this,NguoidungActivity.class);
            startActivity(a);
        }

    }

    public void Huy(View view) {
finish();
    }

    private void initView() {

        edPassWord = (EditText) findViewById(R.id.edPassWord);

    }


}
