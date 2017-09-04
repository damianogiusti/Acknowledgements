package com.damianogiusti.acknowledgements.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.damianogiusti.acknowledgements.Acknowledger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);

        Acknowledger.with(this)
                .load(R.raw.licenses)
                .into(webView);
    }
}
