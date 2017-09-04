package com.damianogiusti.acknowledgements;

import android.content.Context;
import android.support.annotation.RawRes;

import com.damianogiusti.acknowledgements.licenses.License;
import com.damianogiusti.acknowledgements.licenses.LicenseTypes;
import com.damianogiusti.acknowledgements.model.Artifact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damiano Giusti on 01/09/17.
 */
class JsonDatasource {

    private static final String ERROR_PARSING_JSON = "Invalid json file. Check your syntax.";
    private static final String ERROR_PARSING_ARTIFACTS_FROM_JSON = "The provided json file was " +
            "populated in a wrong way. Check your configuration.";
    private static final String ERROR_PARSING_LICENSE = "Invalid License specified in JSON file";

    interface JsonContract {
        String NAME = "name";
        String URL = "url";
        String COPYRIGHT = "copyright";
        String LICENSE = "license";
    }

    private Context context;
    private LicenseMapper licenseMapper;
    @RawRes private int jsonAsset;

    JsonDatasource(Context context, int jsonAsset, LicenseMapper licenseMapper) {
        this.context = context;
        this.jsonAsset = jsonAsset;
        this.licenseMapper = licenseMapper;
    }

    List<Artifact> parseJson() {

        InputStream inputStream = context.getResources().openRawResource(jsonAsset);
        JSONArray jsonArray;
        try {
            jsonArray = readJson(inputStream);
        } catch (JSONException ex) {
            throw new AcknowledgerException(ERROR_PARSING_JSON, ex);
        }

        List<Artifact> artifacts;
        try {
            artifacts = jsonToArtifacts(jsonArray);
        } catch (JSONException ex) {
            throw new AcknowledgerException(ERROR_PARSING_ARTIFACTS_FROM_JSON, ex);
        }

        return artifacts;
    }

    private JSONArray readJson(InputStream inputStream) throws JSONException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new JSONException(e.getLocalizedMessage());
        }
        return new JSONArray(sb.toString());
    }

    private List<Artifact> jsonToArtifacts(JSONArray jsonArray) throws JSONException {
        List<Artifact> artifacts = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String name = jsonObject.optString(JsonContract.NAME, "");
            String url = jsonObject.optString(JsonContract.URL, "");
            String copyright = jsonObject.optString(JsonContract.COPYRIGHT, "");
            @LicenseTypes String rawLicense = jsonObject.optString(JsonContract.LICENSE, "");

            License license;
            try {
                license = licenseMapper.licenseFromString(rawLicense);
            } catch (LicenseMapper.LicenseMappingException e) {
                throw new JSONException(ERROR_PARSING_LICENSE);
            }

            Artifact artifact = new Artifact(name, url, copyright, license);

            artifacts.add(artifact);
        }
        return artifacts;
    }
}
