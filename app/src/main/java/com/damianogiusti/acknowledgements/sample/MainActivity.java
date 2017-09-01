package com.damianogiusti.acknowledgements.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.damianogiusti.acknowledgements.NoticesHtmlBuilder;
import com.damianogiusti.acknowledgements.licenses.ApacheSoftwareLicense20;
import com.damianogiusti.acknowledgements.licenses.MITLicense;
import com.damianogiusti.acknowledgements.model.Notice;
import com.damianogiusti.acknowledgements.model.Notices;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notices notices = new Notices();
        notices.addNotice(new Notice("Dagger 2", "", "Copyright 2012 The Dagger Authors", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("CustomizableCalendar", "", "MOLO17 Srl", new MITLicense()));

        String html = NoticesHtmlBuilder.create(this)
                .setNotices(notices)
                .setShowFullLicenseText(false)
                .build();

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
    }
}
