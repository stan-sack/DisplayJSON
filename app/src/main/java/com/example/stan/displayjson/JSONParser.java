package com.example.stan.displayjson;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by stan on 6/06/16.
 */
public class JSONParser {
    final String TAG = "JsonParser.java";
    static InputStream inputStream = null;
    static JSONObject jObj = null;
    static String json = "";


    public JSONParser(){}

    public JSONObject getJSONFromUrl(String urlSource) {
        //make HTTP request
        try {
            URL url = new URL(urlSource);
            URLConnection urlConnection = (URLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error getting string" + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "Error getting string" + e.toString());
            e.printStackTrace();
        }

        //Read JSON data from inputStream
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e(TAG, "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing data " + e.toString());
        }

        return jObj;// return JSON String
    }

}
