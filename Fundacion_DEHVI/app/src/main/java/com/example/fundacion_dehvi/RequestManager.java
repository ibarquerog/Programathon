package com.example.fundacion_dehvi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
                    UserInfo.dni = username;
                    UserInfo.uid = Integer.parseInt(userInfo.get("uid").toString());
                    UserInfo.name = userInfo.get("givenName").toString();
                    UserInfo.email = userInfo.get("email").toString();
                    UserInfo.role = userInfo.get("role").toString();

                    //funco
                    ref.onRequestLoginResponse(UserInfo.role);
                    Log.i("dddddd", UserInfo.role);
                } catch (JSONException e) {
                    Log.i("dddddd", "ERROR: " + e.toString());
                    e.printStackTrace();
                }

                Log.i("dddddd", result);

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

    public void requestGetTeacherByDNI(){//https://stackoverflow.com/questions/44000212/how-to-send-authorization-header-in-android-using-volley-library
        String requestString = this.connectionString.concat("/Teacher/GetByDNI");
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());

                Toast.makeText(getCurrent(), "" + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error: " + error.getMessage());
                Toast.makeText(getCurrent(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", LoginData.tokenType + " " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestGetMyStudents(ConsultarNinos consultarNinosActivity){//https://stackoverflow.com/questions/44000212/how-to-send-authorization-header-in-android-using-volley-library
        String requestString = this.connectionString.concat("/Student/GetMyStudents");
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                consultarNinosActivity.onResponseGetMyStudents(response);
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                consultarNinosActivity.onResponseGetMyStudents(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestGetMyStudents(ElegirEstudiante consultarNinosActivity){//https://stackoverflow.com/questions/44000212/how-to-send-authorization-header-in-android-using-volley-library
        String requestString = this.connectionString.concat("/Student/GetMyStudents");
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                consultarNinosActivity.onResponseGetMyStudents(response);
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                consultarNinosActivity.onResponseGetMyStudents(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestGetAreas(calificarASQ3Activity calificarASQ3Activity){
        String requestString = this.connectionString.concat("/Areas");
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                calificarASQ3Activity.onGetAreasResult(response);
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                calificarASQ3Activity.onGetAreasResult(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    //Cambiar current
    public void requestGetFormByName(String formName){
        String requestName = formName.replaceAll(" ", "%20");
        requestName = "?formHeaderName=" + requestName;
        Toast.makeText(getCurrent(), requestName, Toast.LENGTH_SHORT).show();
        String requestString = this.connectionString.concat("/Form/GetByName" + requestName);
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                //calificarASQ3Activity.onResponseGetAreas(response);
                try {
                    requestGetAttendanceByFormId(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //calificarASQ3Activity.onResponseGetAreas(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestGetAttendanceByFormId(JSONObject form) throws JSONException{
        String formId = form.getString("id");
        String requestName = formId.replaceAll(" ", "%20");
        requestName = "?formId=" + requestName;
        Toast.makeText(getCurrent(), requestName, Toast.LENGTH_SHORT).show();
        String requestString = this.connectionString.concat("/Attendance/GetByFormId" + requestName);
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){
                //calificarASQ3Activity.onResponseGetAreas(response);
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //calificarASQ3Activity.onResponseGetAreas(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestGetAttendanceByStudentId(String studentId) throws JSONException{
        String requestName = studentId.replaceAll(" ", "%20");
        requestName = "?studentId=" + requestName;
        Toast.makeText(getCurrent(), requestName, Toast.LENGTH_SHORT).show();
        String requestString = this.connectionString.concat("/Attendance/GetByStudentId" + requestName);
        RequestQueue queue = Volley.newRequestQueue(current);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, requestString,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){
                //calificarASQ3Activity.onResponseGetAreas(response);
                Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //calificarASQ3Activity.onResponseGetAreas(null);
                Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }

    public void requestAddResults(JSONObject json, calificarASQ3Activity ref){
        String requestString = this.connectionString.concat("/Result/AddResults");
        RequestQueue queue = Volley.newRequestQueue(current);

        Log.d("aaaaaaaaaaaaaa", json.toString());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, requestString,
                json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                ref.onAddResultsResult(true);
                //Log.d("Response", response.toString());

                //Toast.makeText(getCurrent(), "" + response.toString(), Toast.LENGTH_SHORT).show();
                //Log.i("dddddd", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("Error", "Error: " + error.getMessage());
                //Toast.makeText(getCurrent(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                ref.onAddResultsResult(true);
                //Log.d("dddddd", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + LoginData.accessToken);
                return headers;
            }
        };
        queue.add(req);
    }
}

