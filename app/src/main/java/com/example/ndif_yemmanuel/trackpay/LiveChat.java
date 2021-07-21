package com.example.ndif_yemmanuel.trackpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

//import com.salesforce.android.chat.core.ChatConfiguration;
//import com.salesforce.android.chat.ui.ChatUI;
//import com.salesforce.android.chat.ui.ChatUIClient;
//import com.salesforce.android.chat.ui.ChatUIConfiguration;
//import com.salesforce.android.service.common.utilities.control.Async;

public class LiveChat extends AppCompatActivity {

    public static final String LIVEAGENT = "https://sifmis.ladesk.com/scripts/inline_chat.php?cwid=diavob97";
    WebView webView;
    ProgressBar simpleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chat);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpleBar = findViewById(R.id.progressBar);
        simpleBar.setVisibility(View.VISIBLE);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                simpleBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.loadUrl(LIVEAGENT);

    }

//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(LIVEAGENT);
//            simpleBar.setVisibility(View.INVISIBLE);
//            return true;
//        }
//    }
}
