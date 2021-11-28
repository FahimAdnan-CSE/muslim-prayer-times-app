package com.example.jsone_parse_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    WebView  mWebView;
    ImageView imageView;
    ImageView exit2;
    ImageView reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mWebView=findViewById(R.id.sitesWebView);

        imageView=(ImageView) findViewById(R.id.imageView48);
        exit2=(ImageView) findViewById(R.id.exit2);
        reload=(ImageView) findViewById(R.id.reload);

        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitmethod();

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//               startActivity(intent);

//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

                Intent intent = new Intent(Main2Activity.this ,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

        //mWebView.getSettings().setLoadWithOverviewMode(true);
        //mWebView.getSettings().setUseWideViewPort(true);

        //mWebView.getSettings().setLoadWithOverviewMode(true);
      //  mWebView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

       // mWebView.getSettings().setSupportZoom(true);
        //mWebView.getSettings().setUseWideViewPort(true);
        //mWebView.setInitialScale(50);





        //https://stackoverflow.com/questions/3916330/android-webview-webpage-should-fit-the-device-screen
       //mWebView.setInitialScale(1);

       // mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //mWebView.setScrollbarFadingEnabled(false);

        //

//to change action bar go styles.xml and change to Light.noactionbar
       // mWebView.getSettings().setBuiltInZoomControls(true);
        //mWebView.getSettings().setDisplayZoomControls(false);


        mWebView.loadUrl("http://phpprojecttest.c1.biz/login/login.php");
       // mWebView.loadUrl("http://mytchnobd.c1.biz/");
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mWebView.loadUrl("http://phpprojecttest.c1.biz/test2.php");
//                mWebView.setWebViewClient(new WebViewClient() {
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
//                    return true;
//                }
//            });
                mWebView.reload();
                Toast.makeText(Main2Activity.this,"Successfully Reloaded",Toast.LENGTH_LONG).show();
        }
        });



    }

    //back page in a web page
    @Override

    public void onBackPressed() {

        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            Intent intent = new Intent(Main2Activity.this ,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);

            //exitmethod();
        }
    }


    private void exitmethod() {

        AlertDialog.Builder alertDialogBuilder;

        alertDialogBuilder =new AlertDialog.Builder(Main2Activity.this);

        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(R.string.Dialog);
        alertDialogBuilder.setTitle(R.string.Title);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        alertDialogBuilder.setNegativeButton(" No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        //alertDialogBuilder.setNeutralButton() here i can not use this object

        AlertDialog alertDialog= alertDialogBuilder.create();
        alertDialog.show();




    }












}
