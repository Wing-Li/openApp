package com.lyl.openapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtUri;
    private EditText mEdtMap;
    private EditText mEdtShop;


    private String marketPkg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtUri = (EditText) findViewById(R.id.edt_uri);
        mEdtMap = (EditText) findViewById(R.id.edt_map);
        mEdtShop = (EditText) findViewById(R.id.edt_shop);

        String openTxt = getSp();
        mEdtUri.setText(openTxt);
    }


    /**
     * 打开 Uri
     */
    public void open_uri(View view) {
        String str = mEdtUri.getText().toString().trim();

        saveSp(str);

        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "请确认您输入的Uri是否正确", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 打开应用商店
     */
    public void open_shop(View view) {
        String str = mEdtShop.getText().toString().trim();
        launchAppDetail(str, "");
    }


    /**
     * 打开地图 指定位置
     */
    public void open_map(View view) {
        String str = mEdtMap.getText().toString().trim();
        try {
            Uri mUri = Uri.parse("geo:0,0?q=" + str);
            Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
//            mIntent.setPackage("com.google.android.apps.maps");
            startActivity(mIntent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "请确认您输入的位置名", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //=============================== 存储SharedPreferences ==========================================


    private void saveSp(String str) {
        SharedPreferences sp = getSharedPreferences("uri", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uri", str);
        edit.commit();
    }


    @NonNull
    private String getSp() {
        return getSharedPreferences("uri", 0).getString("uri", "goosee://");
    }

}
