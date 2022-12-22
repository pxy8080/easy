package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pxy.task1220.R;
import com.pxy.task1220.util.RecordHelper;
import com.pxy.task1220.util.SharedPreferencesUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
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
                String c = et3.getText().toString().trim();
                if ((!a.equals("") || !b.equals("") || !c.equals("")) && b.equals(c)) {
                    RecordHelper dbHelper = new RecordHelper(SettingActivity.this, RecordHelper.DATABASE_NAME, null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    String getname = SharedPreferencesUtil.getData(SettingActivity.this, "name", "").toString();
                    //更新数据需要用到类似于Map的ContentValues
                    ContentValues values = new ContentValues();
                    //添加   前面的数据是列名，要与数据库表中的数据保持一致
                    values.put("password", b);
                    int rows = db.update("User", values, "name=? and password=?", new String[]{getname, a});
                    if (rows > 0) {
                        Toast.makeText(SettingActivity.this, "数据更新成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SettingActivity.this, "数据更新失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SettingActivity.this, "输入有问题", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}