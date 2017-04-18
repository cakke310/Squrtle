package com.squrtle.ui.atcitivy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.Toast;

import com.squrtle.R;
import com.squrtle.common.util.DeviceUtils;
import com.squrtle.di.component.AppComponent;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by c_xuwei-010 on 2017/4/17.
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button mBtn;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
//       if( ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
//           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1000);
//       }
//        else {
//           String imei = DeviceUtils.getIMEI(this);
//           Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
//       }
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){
                    String imei = DeviceUtils.getIMEI(LoginActivity.this);
                Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode == 1000){
//            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                String imei = DeviceUtils.getIMEI(this);
//                Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(LoginActivity.this,"用户拒绝授权",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
}
