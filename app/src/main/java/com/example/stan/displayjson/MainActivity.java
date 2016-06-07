package com.example.stan.displayjson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArrayList<TableRow> toDraw;
    ArrayList<TableRow> rows = new ArrayList<TableRow>();




    // contacts JSONArray


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute();




    }


    class RetrieveFeedTask extends AsyncTask<String, Void, Object[]> {
        final String TAG = "AsyncTaskParseJson.java";

        private Exception exception;



        protected Object[] doInBackground(String... urls) {
            // instantiate our json parser
            JSONArray dataJsonArr = null;
            JSONParser jParser = new JSONParser();

            // get json string from url
            JSONObject json = jParser.getJSONFromUrl("https://api.myjson.com/bins/1quht");
            Log.e(TAG, json.toString());
            try {
                // get the array of users
                dataJsonArr = json.getJSONArray("solutions");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String name = c.getString("name");
                    String description = c.getString("description");
                    String icon = c.getString("icon");
                    Bitmap bitmap = null;
                    // show the values in our logcat
                    Log.e(TAG, "name: " + name + ", description: " + description + ", icon: " + icon);
                    try {
                        URL image_url = new URL(icon);
                        URLConnection urlConnection = (URLConnection) image_url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "Error getting string" + e.toString());
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e(TAG, "Error getting string" + e.toString());
                        e.printStackTrace();
                    }

                    TableRow row = new TableRow(name, description, bitmap);
                    Log.e(TAG, row.toString());
                    rows.add(row);
                    Log.e(TAG, rows.toString());
                }
                Object[] rowsList = rows.toArray();
                return rowsList;
            } catch(JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

        protected void onPostExecute(ArrayList<TableRow> rows) {
            toDraw = rows;
        }
    }






}
