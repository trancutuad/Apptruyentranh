package vn.poly.apptruyen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.poly.apptruyen.adapter.TruyenTranhAdapter;
import vn.poly.apptruyen.api.ApiLayTruyen;
import vn.poly.apptruyen.interfaces.LayTruyenVe;
import vn.poly.apptruyen.object.TruyenTranh;
import vn.poly.apptruyen.ui.NguoidungActivity;


public class ManHinhChuActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    ImageView nguoidung;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chu);

        nguoidung = (ImageView)  findViewById(R.id.nguoidung);

        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();

        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChuActivity.this, NguoidungActivity.class);
                startActivity(intent);
            }
        });
    }



     public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Xác nhận thoát");
        alertDialogBuilder.setIcon(R.drawable.nutout);
        alertDialogBuilder.setMessage("Bạn có chắc muốn thoát không");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ManHinhChuActivity.this,"", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog= alertDialogBuilder.create();
        alertDialog.show();



        }




    private void init(){
        truyenTranhArrayList= new ArrayList<>();

        adapter= new TruyenTranhAdapter(this,0, truyenTranhArrayList);
    }
    private void anhXa(){
        gdvDSTruyen= findViewById(R.id.gdvDSTruyen);
        edtTimKiem= findViewById(R.id.edtTimKiem);
    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s= edtTimKiem.getText().toString();
                adapter.sortTruyen(s);

            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenTranh truyenTranh= truyenTranhArrayList.get(i);
                Bundle b= new Bundle();
                b.putSerializable("truyen",truyenTranh);
                Intent intent= new Intent(ManHinhChuActivity.this,ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Đang tải", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter= new TruyenTranhAdapter(this,0, truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Lỗi kết nối", Toast.LENGTH_SHORT).show();

    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }

}
