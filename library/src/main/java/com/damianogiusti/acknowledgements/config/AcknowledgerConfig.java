package com.damianogiusti.acknowledgements.config;

/**
 * Created by Damiano Giusti on 04/09/17.
 */
public class AcknowledgerConfig {

    private String style;
    private boolean shouldUseFullLengthLicense;

    public static class Builder {
        String style;
        boolean shouldUseFullLengthLicense;

        public Builder setCustomStyle(String styleCss) {
            this.style = styleCss;
            return this;
        }

        public Builder useFullLengthLicense(boolean shouldUseFullLengthLicense) {
            this.shouldUseFullLengthLicense = shouldUseFullLengthLicense;
            return this;
        }

        public AcknowledgerConfig build() {
            AcknowledgerConfig config = new AcknowledgerConfig();
            config.style = this.style;
            config.shouldUseFullLengthLicense = this.shouldUseFullLengthLicense;
            return config;
        }
    }

    public String getStyle() {
        return style;
    }

    public boolean useFullLengthLicense() {
        return shouldUseFullLengthLicense;
    }
}
