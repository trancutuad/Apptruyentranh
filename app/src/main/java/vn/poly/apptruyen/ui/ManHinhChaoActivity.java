package vn.poly.apptruyen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import vn.poly.apptruyen.R;


public class ManHinhChaoActivity extends AppCompatActivity {
    private ImageView imghello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);

        CountDownTimer countDownTimer = new CountDownTimer(1500,1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        }.start();

    }
}
