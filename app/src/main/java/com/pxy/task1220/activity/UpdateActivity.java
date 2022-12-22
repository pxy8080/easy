package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pxy.task1220.R;
import com.pxy.task1220.util.SharedPreferencesUtil;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        iv_back.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Intent intent3=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv1:
                Intent intent=new Intent(UpdateActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv2:
                Intent intent2=new Intent(UpdateActivity.this,SettingActivity2.class);
                startActivity(intent2);
                break;
            case R.id.tv3:
                SharedPreferencesUtil.saveData(UpdateActivity.this, "islogin", 0);
                SharedPreferencesUtil.saveData(UpdateActivity.this, "name", "");
                SharedPreferencesUtil.saveData(UpdateActivity.this, "password", "");
                SharedPreferencesUtil.saveData(UpdateActivity.this,"encrypted","");
                SharedPreferencesUtil.saveData(UpdateActivity.this, "anwser", "");
                Toast.makeText(UpdateActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}