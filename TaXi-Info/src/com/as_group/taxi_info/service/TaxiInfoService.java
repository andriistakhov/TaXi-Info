package com.as_group.taxi_info.service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import com.as_group.taxi_info.db.SQLiteContract;
import com.as_group.taxi_info.utils.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 13.07.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class TaxiInfoService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p/>
     * Used to name the worker thread, important only for debugging.
     */
    public TaxiInfoService() {
        super("TaxiInfoService");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String url = bundle.getString(Constants.URL_NAME);

        Map<String, String> params = new HashMap<String, String>();
        String[] paramsKeys = Constants.GET_SERVICE_PARAMS;
        for (int i = 0; i < paramsKeys.length; i++) {
            if (bundle.containsKey(paramsKeys[i])) {
                params.put(paramsKeys[i], bundle.getString(paramsKeys[i]));
            }
        }
        String jsonResult = getInfoFromServer(url, params);
        parseJson(jsonResult);

    }

    private void populateCity(SparseArray<String> map) {
        for (int i = 0; i < map.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SQLiteContract.City.COLUMN_CITY_ID, map.keyAt(i));
            values.put(SQLiteContract.City.COLUMN_CITY_NAME, map.valueAt(i));
            ContentResolver contentResolver = getContentResolver();
            contentResolver.insert(SQLiteContract.City.CONTENT_URI, values);
        }
    }

    private String getInfoFromServer(String url, Map<String, String> params) {
        String url_select = url;

        HttpParams param = new BasicHttpParams();

        for (Map.Entry<String, String> e : params.entrySet()) {
            param.setParameter(e.getKey(), e.getValue());
        }

        InputStream inputStream = null;
        try {
            // Set up HTTP post

            // HttpClient is more then less deprecated. Need to change to URLConnection
            HttpClient httpClient = new DefaultHttpClient();


            HttpGet httpPost = new HttpGet(url_select);
            httpPost.setParams(param);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            // Read content & Log
            inputStream = httpEntity.getContent();
        } catch (UnsupportedEncodingException e1) {
            Log.e("UnsupportedEncodingException", e1.toString());
            e1.printStackTrace();
        } catch (ClientProtocolException e2) {
            Log.e("ClientProtocolException", e2.toString());
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            Log.e("IllegalStateException", e3.toString());
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.e("IOException", e4.toString());
            e4.printStackTrace();
        }
        // Convert response to string using String Builder
        String result = null;
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            result = sBuilder.toString();

        } catch (Exception e) {
            Log.e("StringBuilding & BufferedReader", "Error converting result " + e.toString());
        }

        return result;
    }

    private void parseJson(String jsonString) {
        {
            try {
                JSONArray jArray = new JSONArray(jsonString);

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject jObject = jArray.getJSONObject(i);

                    String name = jObject.getString("name");
                    int id = jObject.getInt("id");
                    int version = jObject.getInt("version");


                } // End Loop


            } catch (JSONException e) {

                Log.e("JSONException", "Error: " + e.toString());

            } // catch (JSONException e)

        }
    }
}
