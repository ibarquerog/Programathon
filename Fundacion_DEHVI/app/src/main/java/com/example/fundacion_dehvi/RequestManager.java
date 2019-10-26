package com.example.fundacion_dehvi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    //Log.d("Payload",credentials.toString(2));
    private String connectionString = "http://192.168.128.20:3001/ApiServer/api/";
    private Context current;

    public RequestManager(Context current) {
        this.current = current;
    }



    public JSONObject requestLogin(String username, String password){

        String requestString = "http://192.168.128.20:3001/ApiServer/api/LogIn";
        RequestQueue queue = Volley.newRequestQueue(current);

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("username", username);
            credentials.put("password", password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestString, credentials, response -> {
                String result = response.toString();

                Log.i("dddddd", result);

            }, (VolleyError error) -> {

                Log.i("dddddd", "ERROR " + error.toString());
            });
            //Space for the request part
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            Log.i("dddddd", "ERROR: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public Context getCurrent() {
        return current;
    }

    public void setCurrent(Context current) {
        this.current = current;
    }
}

