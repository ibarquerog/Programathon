package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class calificarASQ3Activity extends AppCompatActivity {
    private calificarASQ3Activity myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_asq3);

        this.myRef = this;
    }

    public void onResponseGetAreas(JSONArray areas){//students = null in error case!!!
        if(areas == null){
            Toast.makeText(this.getApplicationContext(), areas.toString(), Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this.getApplicationContext(), "No se ha encontrado ninguna area.", Toast.LENGTH_SHORT);
        }
    }
}
