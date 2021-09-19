package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class BrowserApp extends AppCompatActivity {
    WebView Browser;
    EditText link;
    Button btn_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_browser_app);
        Browser=findViewById(R.id.webview);
        link=findViewById(R.id.edittextlink);
        btn_Search=findViewById(R.id.btnSearch);

        Browser.setWebViewClient(new mywebViewClient());
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=link.getText().toString();
                Browser.getSettings().setLoadsImagesAutomatically(true);
                Browser.getSettings().setJavaScriptEnabled(true);
                Browser.loadUrl(url);
            }
        });
    }

    private class mywebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}