package com.example.safedriving;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsFragment_toHome extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng boston = new LatLng(42.3601, -71.0589);
            //googleMap.addMarker(new MarkerOptions().position(boston).title("Marker in Boston"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(boston));
            Results.mMap = googleMap;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_maps, container, false);

//        getWeatherInfo(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

//    public void process(View v){
//        String str = "", dataUrl = "";
//        EditText msgTextField = v.findViewById(R.id.userAddress);
//        str = msgTextField.getText().toString();
//        if (str.toCharArray()[0] >=0 && str.toCharArray()[0] <= 9)
//            dataUrl = "https://api.openweathermap.org/data/2.5/weather?zip=" + str + "&APPID=862f2d6fbd64040628c8035ef9ff5fc9";
//        else
//            dataUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + str + "&APPID=862f2d6fbd64040628c8035ef9ff5fc9";
//        ConnectivityManager connMgr = (ConnectivityManager) v.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//
//        if (networkInfo != null && networkInfo.isConnected()) {
//            new download_Data().execute(dataUrl);
//        } else {
//            Toast.makeText(v.getContext(), "No network connection available", Toast.LENGTH_SHORT);
//        }
//    }
//
//    private class download_Data extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            // params comes from the execute() call: params[0] is the url.
//            try {
//                return download_Data_Helper(urls[0]);
//            } catch (IOException e) {
//                return null;
//            }
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            //TextView out_Temp = (TextView) findViewById(R.id.textView);
//            double temp_in_C = 0;
//            if(result!=null) {
//                try {
//                    Log.i(DEBUG_TAG, "Data " + result);
//                    JSONObject jobj = new JSONObject(result);
//                    JSONObject cord = jobj.getJSONObject("coord");
//                    lat = Double.parseDouble(cord.getString("lat"));
//                    lon = Double.parseDouble(cord.getString("lon"));
//
//                    CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(lat, lon));
//                    CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
//                    mMap.moveCamera(center);
//                    mMap.animateCamera(zoom);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            else{
//                Log.i(DEBUG_TAG, "returned bitmap is null");}
//        }
//    }
//
//    private String download_Data_Helper(String myurl) throws IOException {
//        InputStream is = null;
//        String web_Data = "";
//        int getOut = 0;
//
//        try {
//            URL url = new URL(myurl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //conn.setReadTimeout(10000 /* milliseconds */);
//            //conn.setConnectTimeout(15000 /* milliseconds */);
//            conn.setRequestMethod("GET");
//
//            // Starts the query
//            conn.connect();
//
//            int response = conn.getResponseCode();
//            Log.i(DEBUG_TAG, "The response is: " + response);
//
//            is = conn.getInputStream();
//            Reader reader = new InputStreamReader(is, "UTF-8");
//            while (getOut >= 0) {
//                char[] buffer = new char[100];
//                getOut = reader.read(buffer);
//                web_Data = web_Data + new String(buffer);
//            }
//            return web_Data;
//
//        }catch(Exception e) {
//            Log.i(DEBUG_TAG, e.toString());
//        }finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
//        return null;
//    }
}