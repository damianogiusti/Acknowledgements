package com.damianogiusti.acknowledgements;

import android.content.Context;
import android.support.annotation.RawRes;
import android.webkit.WebView;

import com.damianogiusti.acknowledgements.config.AcknowledgerConfig;
import com.damianogiusti.acknowledgements.model.Artifact;

/**
 * Created by Damiano Giusti on 01/09/17.
 */
public final class Acknowledger {

    public static class AcknowledgementBuilder {
        Context context;
        @RawRes int jsonAsset;
        Iterable<Artifact> artifacts;
        AcknowledgerConfig config;

        private AcknowledgementBuilder(Context context) {
            this.context = context;

            // Create default config first. Will be replaced if `using` method is called.
            config = new AcknowledgerConfig.Builder()
                    .setCustomStyle(context.getResources().getString(R.string.notices_default_style))
                    .useFullLengthLicense(false)
                    .build();
        }

        public AcknowledgementBuilder load(@RawRes int jsonAsset) {
            this.jsonAsset = jsonAsset;
            this.artifacts = null;
            return this;
        }

        public AcknowledgementBuilder load(Iterable<Artifact> artifacts) {
            this.artifacts = artifacts;
            return this;
        }

        public AcknowledgementBuilder using(AcknowledgerConfig config) {
            this.config = config;
            return this;
        }

        public String asHtml() {
            if (artifacts == null) {
                JsonDatasource jsonDatasource = new JsonDatasource(context, jsonAsset, new LicenseMapper());
                artifacts = jsonDatasource.parseJson();
            }

            String html = LicenseHtmlGenerator.create(context)
                    .setArtifacts(artifacts)
                    .setStyle(config.getStyle())
                    .setShowFullLicenseText(config.useFullLengthLicense())
                    .setShowFullLicenseText(false)
                    .build();
            return html;
        }

        public void into(WebView webView) {
            String html = asHtml();
            webView.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
        }
    }

    public static AcknowledgementBuilder with(Context context) {
        return new AcknowledgementBuilder(context);
    }
}
