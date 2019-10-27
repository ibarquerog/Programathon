package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import Concretos.Estudiante;

public class ProfesorMenuActivity extends AppCompatActivity {
    public ArrayList<Estudiante> listaEstudiantes;
    private ProfesorMenuActivity myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_menu);

        this.myRef = this;

        RequestManager requestManager = new RequestManager(myRef.getApplicationContext());
        requestManager.requestGetMyStudents(myRef);
    }

    public void onResponseGetMyStudents(JSONArray students) {//students = null in error case!!!
        String jsonString = students.toString();

        Gson gson = new Gson();
        Estudiante[] estudianteArray = gson.fromJson(jsonString, Estudiante[].class);
        listaEstudiantes = new ArrayList<>(Arrays.asList(estudianteArray));
        for(Estudiante estudiante: listaEstudiantes){
            Log.d("dddd", estudiante.getFirstName());
        }
    }
}
