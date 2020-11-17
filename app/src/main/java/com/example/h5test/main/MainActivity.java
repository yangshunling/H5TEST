package com.example.h5test.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.came.viewbguilib.ButtonBgUi;
import com.example.h5test.R;
import com.example.h5test.webview.WebActivity;
import com.example.h5test.webview.X5Activity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.scwang.wave.MultiWaveHeader;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private ButtonBgUi mNormal;
    private ButtonBgUi mX5;
    private MaterialEditText mAddress;
    private String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EasyPermissions.requestPermissions(this, "请授予权限，否则影响部分使用功能", 100, perms);
        setTotalImmersion();
        initView();
        setLisitener();
    }

    private void initView() {
        mNormal = findViewById(R.id.normal);
        mX5 = findViewById(R.id.x5);
        mAddress = findViewById(R.id.address);
    }

    private void setLisitener() {
        mNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mAddress.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(MainActivity.this, "请输入网页地址", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });
        mX5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mAddress.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(MainActivity.this, "请输入网页地址", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, X5Activity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
