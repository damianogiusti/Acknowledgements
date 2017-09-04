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

package com.damianogiusti.acknowledgements.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.damianogiusti.acknowledgements.licenses.ApacheSoftwareLicense20;
import com.damianogiusti.acknowledgements.licenses.License;
import com.damianogiusti.acknowledgements.licenses.MITLicense;

/**
 * Object which represents a single Artifact to be shown
 * in the dependencies licenses HTML.
 */
public final class Artifact implements Parcelable {

    private String name;
    private String url;
    private String copyright;
    private License license;

    public Artifact(final String name, final License license) {
        this(name, "", "", license);
    }

    public Artifact(final String name, final String copyright, final License license) {
        this(name, "", copyright, license);
    }

    public Artifact(final String name, final String url, final String copyright, final License license) {
        this.name = name;
        this.url = url;
        this.copyright = copyright;
        this.license = license;
    }

    // Setter / Getter

    public void setName(final String name) {
        this.name = name;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    public void setLicense(final License license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCopyright() {
        return copyright;
    }

    public License getLicense() {
        return license;
    }

    // Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(copyright);
        dest.writeSerializable(license);
    }

    private Artifact(final Parcel in) {
        name = in.readString();
        url = in.readString();
        copyright = in.readString();
        license = (License) in.readSerializable();
    }

    public static Creator<Artifact> CREATOR = new Creator<Artifact>() {
        public Artifact createFromParcel(final Parcel source) {
            return new Artifact(source);
        }

        public Artifact[] newArray(final int size) {
            return new Artifact[size];
        }
    };

    // Static factory methods

    /**
     * Creates an Apache 2.0 licensed Artifact.
     *
     * @param name      Name of the Artifact
     * @param url       Url to website
     * @param copyright Copyright signature
     * @return the Apache 2.0 artifact
     */
    public static Artifact apache2(final String name, final String url, final String copyright) {
        return new Artifact(name, url, copyright, new ApacheSoftwareLicense20());
    }

    /**
     * Creates an Apache 2.0 licensed Artifact, with an empty URL.
     *
     * @param name      Name of the Artifact
     * @param copyright Copyright signature
     * @return the Apache 2.0 artifact
     */
    public static Artifact apache2(final String name, final String copyright) {
        return apache2(name, "", copyright);
    }

    /**
     * Creates an Apache 2.0 licensed Artifact, without Copyright and URL.
     *
     * @param name Name of the Artifact
     * @return the Apache 2.0 artifact
     */
    public static Artifact apache2(final String name) {
        return apache2(name, "", "");
    }

    /**
     * Creates an MIT licensed Artifact.
     *
     * @param name      Name of the Artifact
     * @param url       Url to website
     * @param copyright Copyright signature
     * @return the MIT artifact
     */
    public static Artifact mit(final String name, final String url, final String copyright) {
        return new Artifact(name, url, copyright, new MITLicense());
    }

    /**
     * Creates an MIT licensed Artifact, with an empty URL.
     *
     * @param name      Name of the Artifact
     * @param copyright Copyright signature
     * @return the MIT artifact
     */
    public static Artifact mit(final String name, final String copyright) {
        return mit(name, "", copyright);
    }

    /**
     * Creates an MIT licensed Artifact, without Copyright and URL.
     *
     * @param name Name of the Artifact
     * @return the MIT artifact
     */
    public static Artifact mit(final String name) {
        return mit(name, "", "");
    }
}
