package com.example.programathon_dehvi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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
            credentials.put("password", username);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestString, credentials, response -> {
                try {
                    String result = response.get("token").toString();

                    Log.d("Result", result.toString());

                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }, error -> {
                Log.d("Result", "ERROR");
            });
            //Space for the request part
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            Log.d("Result", "ERROR");
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

