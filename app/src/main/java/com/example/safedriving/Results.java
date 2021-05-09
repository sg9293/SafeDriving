package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Results extends AppCompatActivity {

    int op = 0;
    static GoogleMap mMap;
    double bac = 0.0;
    double lat = 0.0, lon = 0.0;
    static double output_print_bac = 0.0;
    String DEBUG_TAG = "Debugging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        if (User_Data.gender_const != 0.0) {
            bac = (SummaryHour.alcohol_consumed * 5.14) / (User_Data.user_weight * User_Data.gender_const) - 0.015 * SummaryHour.timeSpent;
        } else if (firstSign.gender_cont != 0.0) {
            bac = (SummaryHour.alcohol_consumed * 5.14) / (firstSign.user_weigh * firstSign.gender_cont) - 0.015 * SummaryHour.timeSpent;
        } else {
            bac = (SummaryHour.alcohol_consumed * 5.14) / (MainActivity.usr_weight * MainActivity.gen_const) - 0.015 * SummaryHour.timeSpent;
        }

        output_print_bac = bac;

        if (bac >= 0.08) {
            op = 1;
            fragmentSelector(op);
        }
        else {
            op = 2;
            fragmentSelector(op);
        }
        bac = 0.0;
    }


    public void fragmentSelector(int position) {
        position = op;
        if (position == 1) {
            Uber_lyft conversionFrag = new Uber_lyft();
            Bundle args = new Bundle();
            args.putInt(Uber_lyft.ARG_POSITION, position);
            conversionFrag.setArguments(args);

            resultDiplay conversionFrag2 = new resultDiplay();
            Bundle args2 = new Bundle();
            conversionFrag2.setArguments(args2);

            // Commands to replace the fragment with this fragment and initializing the
            // process.
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.top_container, conversionFrag2);
            ft.replace(R.id.bottom_container, conversionFrag);
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (position == 2) {
            Log.i("Maps reached", String.valueOf(bac));
            MapsFragment_toHome conversionFrag = new MapsFragment_toHome();
            Bundle args = new Bundle();
            conversionFrag.setArguments(args);

            resultDiplay conversionFrag2 = new resultDiplay();
            Bundle args2 = new Bundle();
            conversionFrag2.setArguments(args2);

            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.top_container, conversionFrag2);
            ft.replace(R.id.bottom_container, conversionFrag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void process(View v){
        String str = "", dataUrl = "";
        EditText msgTextField = findViewById(R.id.userAddress);
        str = msgTextField.getText().toString();
        String customizedstr = str.replaceAll(" ", "+");
        customizedstr = customizedstr.replaceAll(",", "%2C");
        dataUrl = "http://www.mapquestapi.com/geocoding/v1/address?key=lvl3A5sGHBsQMxlrxaVU2ZDC3z5XA8KL&location=" + customizedstr;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new download_Data().execute(dataUrl);
        } else {
            Toast.makeText(getApplicationContext(), "No network connection available", Toast.LENGTH_SHORT);
        }
    }

    private class download_Data extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return download_Data_Helper(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //TextView out_Temp = (TextView) findViewById(R.id.textView);

            if(result!=null) {
                try {
                    JSONObject jobj = new JSONObject(result);
                    JSONArray resul = jobj.getJSONArray("results");
                    JSONObject latLng = resul.getJSONObject(0).getJSONArray("locations").getJSONObject(0).getJSONObject("latLng");
                    lat = Double.parseDouble(latLng.getString("lat"));
                    lon = Double.parseDouble(latLng.getString("lng"));

                    LatLng home = new LatLng(lat, lon);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                    mMap.addMarker(new MarkerOptions().position(home).title("Marker at Home"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.i(DEBUG_TAG, "returned bitmap is null");}
        }
    }

    private String download_Data_Helper(String myurl) throws IOException {
        InputStream is = null;
        String web_Data = "";
        int getOut = 0;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //conn.setReadTimeout(10000 /* milliseconds */);
            //conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");

            // Starts the query
            conn.connect();

            int response = conn.getResponseCode();
            Log.i(DEBUG_TAG, "The response is: " + response);

            is = conn.getInputStream();
            Reader reader = new InputStreamReader(is, "UTF-8");
            while (getOut >= 0) {
                char[] buffer = new char[100];
                getOut = reader.read(buffer);
                web_Data = web_Data + new String(buffer);
            }
            return web_Data;

        }catch(Exception e) {
            Log.i(DEBUG_TAG, e.toString());
        }finally {
            if (is != null) {
                is.close();
            }
        }

        return null;
    }
}