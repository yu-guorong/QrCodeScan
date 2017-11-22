package com.yugrdev.qrdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zbar.lib.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        checkPermission(this, permissions, 0);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CaptureActivity.class));
            }
        });
    }
        /**
         * @param activity              当前Activity 需要在Activity中 重写onRequestPermissionsResult()方法实现回调
         * @param permissions           权限名 android.manifest.permission
         * @param permissionRequestCode 请求码
         * @return true 系统低于M or 未授权 false 已授权
         */
        public static boolean checkPermission(@NonNull Activity activity, @NonNull String[] permissions, @NonNull int permissionRequestCode) {
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
                for (String perms : permissions) {
                    if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(activity, perms)) {
                        activity.requestPermissions(permissions, permissionRequestCode);
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
}
