package com.damianogiusti.acknowledgements;

import com.damianogiusti.acknowledgements.licenses.ApacheSoftwareLicense20;
import com.damianogiusti.acknowledgements.licenses.BSD2ClauseLicense;
import com.damianogiusti.acknowledgements.licenses.BSD3ClauseLicense;
import com.damianogiusti.acknowledgements.licenses.CreativeCommonsAttribution30Unported;
import com.damianogiusti.acknowledgements.licenses.CreativeCommonsAttributionNoDerivs30Unported;
import com.damianogiusti.acknowledgements.licenses.CreativeCommonsAttributionShareAlike30Unported;
import com.damianogiusti.acknowledgements.licenses.EclipsePublicLicense10;
import com.damianogiusti.acknowledgements.licenses.GnuGeneralPublicLicense20;
import com.damianogiusti.acknowledgements.licenses.GnuGeneralPublicLicense30;
import com.damianogiusti.acknowledgements.licenses.GnuLesserGeneralPublicLicense21;
import com.damianogiusti.acknowledgements.licenses.GnuLesserGeneralPublicLicense3;
import com.damianogiusti.acknowledgements.licenses.ISCLicense;
import com.damianogiusti.acknowledgements.licenses.License;
import com.damianogiusti.acknowledgements.licenses.LicenseTypes;
import com.damianogiusti.acknowledgements.licenses.MITLicense;
import com.damianogiusti.acknowledgements.licenses.MozillaPublicLicense11;
import com.damianogiusti.acknowledgements.licenses.MozillaPublicLicense20;
import com.damianogiusti.acknowledgements.licenses.SILOpenFontLicense11;

/**
 * <p>Mapper for obtaining a License instance from a given string.</p>
 * <p>
 * Created by Damiano Giusti on 04/09/17.
 */
final class LicenseMapper {

    static class LicenseMappingException extends Exception {}

    License licenseFromString(@LicenseTypes String rawLicense) throws LicenseMappingException {
        switch (rawLicense) {
            case LicenseTypes.APACHE_2:
                return new ApacheSoftwareLicense20();
            case LicenseTypes.BSD_2:
                return new BSD2ClauseLicense();
            case LicenseTypes.BSD_3:
                return new BSD3ClauseLicense();
            case LicenseTypes.CREATIVE_COMMONS_3:
                return new CreativeCommonsAttribution30Unported();
            case LicenseTypes.CREATIVE_COMMONS_NO_DERIVATIVE_WORKS:
                return new CreativeCommonsAttributionNoDerivs30Unported();
            case LicenseTypes.CREATIVE_COMMONS_SHARE_ALIKE:
                return new CreativeCommonsAttributionShareAlike30Unported();
            case LicenseTypes.ECLIPSE:
                return new EclipsePublicLicense10();
            case LicenseTypes.GNU_GPL_2:
                return new GnuGeneralPublicLicense20();
            case LicenseTypes.GNU_GPL_3:
                return new GnuGeneralPublicLicense30();
            case LicenseTypes.GNU_LGPL_2_1:
                return new GnuLesserGeneralPublicLicense21();
            case LicenseTypes.GNU_LGPL_3:
                return new GnuLesserGeneralPublicLicense3();
            case LicenseTypes.ISC:
                return new ISCLicense();
            case LicenseTypes.MIT:
                return new MITLicense();
            case LicenseTypes.MOZILLA_1_1:
                return new MozillaPublicLicense11();
            case LicenseTypes.MOZILLA_2:
                return new MozillaPublicLicense20();
            case LicenseTypes.SIL:
                return new SILOpenFontLicense11();
        }

        throw new LicenseMappingException();
    }
}
