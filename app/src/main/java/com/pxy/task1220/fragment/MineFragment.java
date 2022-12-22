package com.pxy.task1220.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pxy.task1220.R;
import com.pxy.task1220.activity.LoginActivity;
import com.pxy.task1220.activity.UpdateActivity;
import com.pxy.task1220.util.SharedPreferencesUtil;


public class MineFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        TextView tv_login = view.findViewById(R.id.tv_login);
        int islogin = (int) SharedPreferencesUtil.getData(getContext(), "islogin", 0);
        if (islogin == 1) tv_login.setVisibility(View.GONE);
        else tv_login.setVisibility(View.VISIBLE);
//        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
//        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
//            case R.id.tv1:
//                Intent intent2 = new Intent(getContext(), PlayActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.tv2:
                Intent intent3 = new Intent(getContext(), UpdateActivity.class);
                startActivity(intent3);
                break;
        }
    }
}