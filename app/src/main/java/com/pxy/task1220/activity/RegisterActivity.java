package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.pxy.task1220.R;
import com.pxy.task1220.util.RecordHelper;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatImageView iv_back;
    private EditText name;
    private EditText password;
    private EditText password_again;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        password_again = findViewById(R.id.password_again);
        btn_register = findViewById(R.id.btn_register);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        btn_register.setOnClickListener(view -> {
            String a = name.getText().toString().trim();
            String b = password.getText().toString().trim();
            String c = password_again.getText().toString().trim();
            if (a.equals("") || b.equals("") || c.equals(""))
                Toast.makeText(RegisterActivity.this, "有输入是空", Toast.LENGTH_SHORT).show();
            else if (!b.equals(a))
                Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            else {
                //依靠DatabaseHelper带全部参数的构造函数创建数据库
                RecordHelper dbHelper = new RecordHelper(RegisterActivity.this, RecordHelper.DATABASE_NAME, null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("User", null, "name=?", new String[]{a}, null, null, null);
                if (cursor.getCount()>0){
                    Toast.makeText(RegisterActivity.this, "该用户已经注册过", Toast.LENGTH_SHORT).show();
                }
                else {
                    //添加数据需要用到类似于Map的ContentValues
                    ContentValues values = new ContentValues();
                    //添加   前面的数据是列名，要与数据库表中的数据保持一致
                    values.put("name", a);
                    values.put("password", b);
                    values.put("encrypted", "");
                    values.put("answer", "");
                    //插入数据,第一个参数为表名，中间的参数输入一个空值就行，最后一个参数就是刚才存的values
                    //输入数据会返回一个影响行数
                    long rows = db.insert("User", null, values);
                    if (rows > 0) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}