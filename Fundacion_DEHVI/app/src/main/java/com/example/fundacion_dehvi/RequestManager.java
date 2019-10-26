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

import java.io.IOException;

public class RequestManager {
    //Log.d("Payload",credentials.toString(2));
    private String connectionString = "http://192.168.128.20:3001/ApiServer/api";
    private Context current;

    public RequestManager(Context current) {
        this.current = current;
    }

    public void requestLogin(String username, String password, Login ref) {

        String requestString = this.connectionString.concat("/LogIn");
        RequestQueue queue = Volley.newRequestQueue(current);

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("username", username);
            credentials.put("password", password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestString, credentials, response -> {
                String result = response.toString();

                JSONObject loginData = null;
                try {
                    loginData = response.getJSONObject("LoginData");
                    LoginData.accessToken = loginData.get("access_token").toString();
                    LoginData.expiresIn = Integer.parseInt(loginData.get("expires_in").toString());
                    LoginData.tokenType = loginData.get("token_type").toString();
                    LoginData.refreshToken = loginData.get("refresh_token").toString();
                    LoginData.scope = loginData.get("scope").toString();

                    JSONObject userInfo = response.getJSONObject("UserInfo");
                    UserInfo.uid = Integer.parseInt(userInfo.get("uid").toString());
                    UserInfo.name = userInfo.get("givenName").toString();
                    UserInfo.email = userInfo.get("email").toString();
                    UserInfo.role = userInfo.get("role").toString();

                    //funco
                    ref.onRequestLoginResponse(UserInfo.role);
                    Log.i("dddddd", UserInfo.role);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.i("dddddd", result);

            }, (VolleyError error) -> {
                ref.onRequestLoginResponse("");
                Log.i("dddddd", "ERROR " + error.toString());
            });
            //Space for the request part
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            ref.onRequestLoginResponse("");
            Log.i("dddddd", "ERROR: " + e.toString());
            e.printStackTrace();
        }
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

