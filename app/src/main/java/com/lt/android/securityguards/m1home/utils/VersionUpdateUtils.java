package com.lt.android.securityguards.m1home.utils;

import android.app.Activity;

import com.lt.android.securityguards.m1home.entity.VersionEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

<<<<<<< HEAD
//test

=======
>>>>>>> ff867b4c5125e529c002d39676d6af6d869276e1
/**
 * Created by lt on 2017/10/20.
 */

public class VersionUpdateUtils {
    //本地版本号
    private String mVersion;
    private Activity context;
    private VersionEntity versionEntity;

    public VersionUpdateUtils(String mVersion, Activity context) {
        this.mVersion = mVersion;
        this.context = context;
    }

    //获取服务器版本号
    public void getCloudVersion() {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            //连接超时
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 5000);
            //请求超时
            HttpConnectionParams.setSoTimeout(httpClient.getParams(), 5000);
            HttpGet httpGet = new HttpGet("http://android2017.duapp.com/updateinfo.html");
            HttpResponse excute = httpClient.execute(httpGet);
            if (excute.getStatusLine().getStatusCode() == 200) {
                //请求和响应都成功了
                HttpEntity entity = excute.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                //创建jsonObject对象
                JSONObject jsonObject = new JSONObject(result);
                versionEntity = new VersionEntity();
                versionEntity.versionCode = jsonObject.getString("code");
                versionEntity.description = jsonObject.getString("des");
                versionEntity.apklurl = jsonObject.getString("apkurl");
                if (!mVersion.equals(versionEntity.versionCode)) {
                    //版本不同需升级
                    System.out.println(versionEntity.description);
                    DownloadUtils downloadUtils = new DownloadUtils();
                    downloadUtils.downloadApk(versionEntity.apklurl, "mobileguard.apk", context);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
