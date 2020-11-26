package com.cindy.myfirstandroidtvapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.paul623.wdsyncer.SyncConfig;
import com.thegrizzlylabs.sardineandroid.DavResource;
import com.thegrizzlylabs.sardineandroid.Sardine;
import com.thegrizzlylabs.sardineandroid.impl.OkHttpSardine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WebDavActivity extends Activity {

    private String serverHostUrl;
    private String userName;
    private String password;
    private Sardine sardine;
    //private ArrayList<String> listNames;
    private String listNames;
    private String text;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webdav);
        imageView=findViewById(R.id.image);
        serverHostUrl = "http://iottalk.cmoremap.com.tw:6333/qbee205/remote.php/dav/files/imac";
        userName = "imac";
        password = "imacuser";
        Toast.makeText(this,"讀取中，請稍後",Toast.LENGTH_SHORT).show();
        Thread t = new Thread(runnable);
        t.start();

    }




    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            SyncConfig config=new SyncConfig(WebDavActivity.this);
            config.setUserAccount(userName);
            config.setPassWord(password);
            config.setServerUrl(serverHostUrl);

            Sardine sardine= new OkHttpSardine();
            sardine.setCredentials(userName,password);
            try {

                InputStream inputStream2 = sardine.get(serverHostUrl + "/Photos/g/57135839_p0.png");
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);
                Log.d("123","YES\n"+inputStream2);
                final Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream2);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bmp);
                    }
                });

//                InputStream inputStream = sardine.get(serverHostUrl + "/Photos/g/3s.mp4");
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//                File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/capturevideoandpictureandsaveandchoose/");
//                if (!root.exists()) {
//                    root.mkdirs();
//                }
//                String sFileName="av87"+".mp4";
//                byte[] buffer = new byte[1024];
//                int bufferLength = 0;
//                File logttt = new File(root, sFileName);
//                FileOutputStream fileOutput = new FileOutputStream(logttt);
//                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
//                    fileOutput.write(buffer, 0, bufferLength);
//                }
//                //close the output stream when complete //
//                fileOutput.close();


            } catch (IOException e) {
                e.printStackTrace();
                Log.d("123","請求失败:"+e);
            }

            List<DavResource> resources = null;
            try {
                resources = sardine.list(serverHostUrl);
                for (DavResource res : resources)
                {
                    listNames=listNames+res+"\n";
                }
                Log.d("123","YES\n"+listNames);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("123","請求失败:"+e);
            }

//下載
//            SyncManager syncManager=new SyncManager(WebDavActivity.this);
//            syncManager.downloadString("57135839_p0.png", "/Photos/g", new OnSyncResultListener() {
//                @Override
//                public void onSuccess(String result) {
//                    Looper.prepare();
//                    Log.d("123","YES"+result);
//                    Looper.loop();
//                }
//
//                @Override
//                public void onError(String errorMsg) {
//                    Looper.prepare();
//                    Log.d("123","請求失败:"+errorMsg);
//                    Looper.loop();
//                }
//            });

//列表
//            SyncManager syncManager=new SyncManager(WebDavActivity.this);
//            syncManager.listAllFile("/Photos", new OnListFileListener() {
//                @Override
//                public void listAll(List<DavData> davResourceList) {
//                    text="";
//                    for(DavData i:davResourceList){
//                        text=text+i.getDisplayName()+"\n";
//                        Log.d("123",i.toString());
//                    }
////                Message message=new Message();
////                message.what=1;
////                handler.sendMessage(message);
//                }
//                @Override
//                public void onError(String errorMsg) {
//                    Log.d("123","請求失败:"+errorMsg);
//                }
//            });



       }
    };

}
