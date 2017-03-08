# OpenApp
Android 打开 Schema。
***
App 截图：
![](https://github.com/Wing-Li/openApp/blob/master/img/openApp.png)


### 打开 URI 链接

    Intent intent = new Intent();
    intent.setAction("android.intent.action.VIEW");
    intent.setData(Uri.parse(str));
    startActivity(intent);

### 打开 应用商店详情页

    Uri uri = Uri.parse("market://details?id=" + appPkg);
    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    // 使用 GooglePlay 打开，不设置会让选择手机内安装的应用商店
    intent.setPackage("com.android.vending");
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);

### 打开 指定地图位置

    Uri mUri = Uri.parse("geo:0,0?q=" + str);
    Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
    // 使用 GoogleMap 打开，不设置会让选择手机内安装的地图
    mIntent.setPackage("com.google.android.apps.maps");
    startActivity(mIntent);

