package com.damianogiusti.acknowledgements.licenses;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Damiano Giusti on 02/09/17.
 */
@StringDef({LicenseTypes.APACHE_2, LicenseTypes.BSD_2, LicenseTypes.BSD_3,
        LicenseTypes.CREATIVE_COMMONS_3, LicenseTypes.CREATIVE_COMMONS_NO_DERIVATIVE_WORKS,
        LicenseTypes.CREATIVE_COMMONS_SHARE_ALIKE, LicenseTypes.ECLIPSE, LicenseTypes.GNU_GPL_2,
        LicenseTypes.GNU_GPL_3, LicenseTypes.GNU_LGPL_2_1, LicenseTypes.GNU_LGPL_3, LicenseTypes.ISC,
        LicenseTypes.MIT, LicenseTypes.MOZILLA_1_1, LicenseTypes.MOZILLA_2, LicenseTypes.SIL})
@Retention(RetentionPolicy.SOURCE)
public @interface LicenseTypes {
    String APACHE_2 = "apache2";
    String BSD_2 = "bsd2";
    String BSD_3 = "bsd3";
    String CREATIVE_COMMONS_3 = "cc3";
    String CREATIVE_COMMONS_NO_DERIVATIVE_WORKS = "cc3NoDerivativeWorks";
    String CREATIVE_COMMONS_SHARE_ALIKE = "cc3ShareAlike";
    String ECLIPSE = "eclipse";
    String GNU_GPL_2 = "gnuGpl2";
    String GNU_GPL_3 = "gnuGpl3";
    String GNU_LGPL_3 = "gnuLgpl3";
    String GNU_LGPL_2_1 = "gnuLgpl21";
    String ISC = "isc";
    String MIT = "mit";
    String MOZILLA_1_1 = "mozilla11";
    String MOZILLA_2 = "mozilla2";
    String SIL = "sil";
}
