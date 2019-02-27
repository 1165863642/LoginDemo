package com.example.a11658.logindemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_login, btn_cancel;
    EditText et_user, et_pass;
    CheckBox cb_remember;
    SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //初始化控件
    private void initView() {
        spf = getSharedPreferences("test", MODE_PRIVATE);
        //关联控件
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_login = findViewById(R.id.btn_login);
        et_pass = findViewById(R.id.et_pass);
        et_user = findViewById(R.id.et_user);
        cb_remember = findViewById(R.id.cb_remember);
        et_user.setText(spf.getString("username", "111"));
        //点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登陆
                //1.获取用户名密码
                String username = et_user.getText().toString().trim();
                String password = et_pass.getText().toString().trim();
                //2.判断是否记住用户名
                if (cb_remember.isChecked()) {
                    spf.edit().putString("username", username).commit();
                } else {
                    spf.edit().clear().commit();
                }

                //3.判断用户名密码是否正确
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    if (username.equals("user") && password.equals("pass")) {
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "用户名密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //文字改变前
                Toast.makeText(MainActivity.this,"请输入", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //正在输入
                Toast.makeText(MainActivity.this,"正在输入", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //输入结束后
                Toast.makeText(MainActivity.this,"输入结束后", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
