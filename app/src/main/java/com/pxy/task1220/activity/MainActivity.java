package com.pxy.task1220.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pxy.task1220.R;
import com.pxy.task1220.fragment.CourseFragment;
import com.pxy.task1220.fragment.ExerciseFragment;
import com.pxy.task1220.fragment.MineFragment;
import com.pxy.task1220.util.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView bottomNavigationView;
    private CourseFragment courseFragment;
    private ExerciseFragment exerciseFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        bottomNavigationView.setSelectedItemId(R.id.mine);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.course:
                    replaceFragment(courseFragment);
                    break;
                case R.id.exercises:
                    replaceFragment(exerciseFragment);
                    break;
                case R.id.mine:
                    replaceFragment(mineFragment);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.navigation);
        courseFragment = new CourseFragment();
        exerciseFragment = new ExerciseFragment();
        mineFragment = new MineFragment();
        replaceFragment(mineFragment);
    }


    //切换不同的fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}