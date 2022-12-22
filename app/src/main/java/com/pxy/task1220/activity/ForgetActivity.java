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
import android.widget.Toast;

import com.pxy.task1220.R;
import com.pxy.task1220.util.RecordHelper;
import com.pxy.task1220.util.SharedPreferencesUtil;

public class ForgetActivity extends AppCompatActivity {
    private EditText name;
    private EditText answer;
    private EditText password;
    private Button btn_update;
    private AppCompatImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view -> {
            Intent intent = new Intent(ForgetActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        name = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        password = findViewById(R.id.password);
        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(view -> {
            String a = answer.getText().toString().trim();
            String name11 = name.getText().toString().trim();
            String b = password.getText().toString().trim();
            if (a.equals(""))
                Toast.makeText(ForgetActivity.this, "输入是空", Toast.LENGTH_SHORT).show();
            else {
                RecordHelper dbHelper = new RecordHelper(ForgetActivity.this, RecordHelper.DATABASE_NAME, null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //更新数据需要用到类似于Map的ContentValues
                ContentValues values = new ContentValues();
                //添加   前面的数据是列名，要与数据库表中的数据保持一致
                values.put("password", b);
                int rows = db.update("User", values, "name=? and Answer=?", new String[]{name11, a});
                if (rows > 0) {
                    Toast.makeText(ForgetActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgetActivity.this, "答案不对", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}