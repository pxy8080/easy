package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.pxy.task1220.R;
import com.pxy.task1220.util.RecordHelper;
import com.pxy.task1220.util.SharedPreferencesUtil;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText password;
    private TextView btn_register;
    private MaterialButton btn_login;
    private TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        tv_forget = findViewById(R.id.tv_forget);
        btn_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);

        btn_register.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget:
                Intent intent_forget = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent_forget);
                break;
            case R.id.btn_login:
                String a = name.getText().toString().trim();
                String b = password.getText().toString().trim();
                if (a.equals("") || b.equals(""))
                    Toast.makeText(LoginActivity.this, "输入有空", Toast.LENGTH_SHORT).show();
                else {
                    RecordHelper dbHelper = new RecordHelper(LoginActivity.this, RecordHelper.DATABASE_NAME, null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("User", null, "name=?", new String[]{a}, null, null, null);
                    if (cursor.moveToFirst()) {
                        //遍历游标
                        for (int i = 0; i < cursor.getCount(); i++) {
                            cursor.move(i);
                            String username1 = cursor.getString(0);
                            //获得密码
                            String password1 = cursor.getString(1);
                            String encrypted = cursor.getString(2);
                            String anwser = cursor.getString(3);
                            if (username1.equals(a) && password1.equals(b)) {
                                Intent intent_main = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent_main);
                                SharedPreferencesUtil.saveData(LoginActivity.this,"islogin",1);
                                SharedPreferencesUtil.saveData(LoginActivity.this,"name",username1);
                                SharedPreferencesUtil.saveData(LoginActivity.this,"password",password1);
                                SharedPreferencesUtil.saveData(LoginActivity.this,"encrypted",encrypted);
                                SharedPreferencesUtil.saveData(LoginActivity.this,"anwser",anwser);
                            }
                        }
                    }
                }


                break;
        }
    }
}