/*
 * Copyright 2013 Philip Schiffer
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.damianogiusti.acknowledgements;

import android.content.Context;

import com.damianogiusti.acknowledgements.licenses.License;
import com.damianogiusti.acknowledgements.model.Artifact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

final class LicenseHtmlGenerator {

    private Context context;
    private final Map<License, String> licenseTextCache = new HashMap<>();
    private Iterable<Artifact> artifactList = new ArrayList<>();
    private String style;
    private boolean shouldShowFullLicenseText;

    public static LicenseHtmlGenerator create(final Context context) {
        return new LicenseHtmlGenerator(context);
    }

    private LicenseHtmlGenerator(final Context context) {
        this.context = context;
    }

    /**
     * Sets the artifacts whose license will be generated.
     */
    public LicenseHtmlGenerator setArtifacts(final Iterable<Artifact> artifacts) {
        artifactList = artifacts;
        return this;
    }

    /**
     * Sets the style used to display the license information.
     *
     * @param style CSS style
     */
    public LicenseHtmlGenerator setStyle(final String style) {
        this.style = style;
        return this;
    }

    /**
     * Sets whether the license will be shown as full text or in a short version.
     */
    public LicenseHtmlGenerator setShowFullLicenseText(final boolean showFullLicenseText) {
        shouldShowFullLicenseText = showFullLicenseText;
        return this;
    }

    /**
     * Generates the HTML string with the given artifacts and parameters.
     * @return
     */
    public String build() {
        if (style == null) {
            throw new IllegalStateException("Cannot build HTML without style");
        }

        final StringBuilder noticesHtmlBuilder = new StringBuilder(500);
        appendNoticesContainerStart(noticesHtmlBuilder);
        if (artifactList != null) {
            for (final Artifact artifact : artifactList) {
                appendNoticeBlock(noticesHtmlBuilder, artifact);
            }
        } else {
            throw new IllegalStateException("no notice(s) set");
        }
        appendNoticesContainerEnd(noticesHtmlBuilder);
        return noticesHtmlBuilder.toString();
    }

    // Utilities

    private void appendNoticesContainerStart(final StringBuilder noticesHtmlBuilder) {
        noticesHtmlBuilder.append("<!DOCTYPE html><html><head>")
                .append("<style type=\"text/css\">").append(style).append("</style>")
                .append("</head><body>");
    }

    private void appendNoticeBlock(final StringBuilder noticesHtmlBuilder, final Artifact artifact) {
        noticesHtmlBuilder.append("<ul><li>").append(artifact.getName());
        final String currentNoticeUrl = artifact.getUrl();
        if (currentNoticeUrl != null && currentNoticeUrl.length() > 0) {
            noticesHtmlBuilder.append(" (<a href=\"")
                    .append(currentNoticeUrl)
                    .append("\" target=\"_blank\">")
                    .append(currentNoticeUrl)
                    .append("</a>)");
        }
        noticesHtmlBuilder.append("</li></ul>");
        noticesHtmlBuilder.append("<pre>");
        final String copyright = artifact.getCopyright();
        if (copyright != null) {
            noticesHtmlBuilder.append(copyright).append("<br/><br/>");
        }
        noticesHtmlBuilder.append(getLicenseText(artifact.getLicense())).append("</pre>");
    }

    private void appendNoticesContainerEnd(final StringBuilder noticesHtmlBuilder) {
        noticesHtmlBuilder.append("</body></html>");
    }

    private String getLicenseText(final License license) {
        if (license != null) {
            if (!licenseTextCache.containsKey(license)) {
                licenseTextCache.put(license, shouldShowFullLicenseText
                        ? getFullTextLicense(license)
                        : getSummaryTextLicense(license));
            }
            return licenseTextCache.get(license);
        }
        return "";
    }

    private String getFullTextLicense(License license) {
        return license.getFullText(context);
    }

    private String getSummaryTextLicense(License license) {
        return license.getSummaryText(context);
    }
}
