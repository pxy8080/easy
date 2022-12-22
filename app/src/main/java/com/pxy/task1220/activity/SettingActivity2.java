package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pxy.task1220.R;
import com.pxy.task1220.util.RecordHelper;
import com.pxy.task1220.util.SharedPreferencesUtil;

public class SettingActivity2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private EditText et1;
    private EditText et2;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);

        initView();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btn_update = findViewById(R.id.btn_update);

        iv_back.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.btn_update:
                String a = et1.getText().toString().trim();
                String b = et2.getText().toString().trim();
                if (!a.equals("") || !b.equals("")) {
                    RecordHelper dbHelper = new RecordHelper(SettingActivity2.this, RecordHelper.DATABASE_NAME, null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    String getname = SharedPreferencesUtil.getData(SettingActivity2.this, "name", "").toString();

                    ContentValues values = new ContentValues();
                    //添加   前面的数据是列名，要与数据库表中的数据保持一致
                    values.put("encrypted", a);
                    values.put("Answer", b);
                    int rows = db.update("User", values, "name=?", new String[]{getname});
                    if (rows > 0) {
                        Toast.makeText(SettingActivity2.this, "数据更新成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SettingActivity2.this, "数据更新失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SettingActivity2.this, "输入有问题", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}