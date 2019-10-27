package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import Concretos.Estudiante;

public class ElegirEstudiante extends AppCompatActivity {

    private ElegirEstudiante myRef;
    public ArrayList<Estudiante> listaEstudiantes;
    private String estudiantesJsonString;
    Spinner spinnerDeEstudiantes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_estudiante);
        this.myRef = this;
        spinnerDeEstudiantes = findViewById(R.id.spinnerEstudiantes);

        RequestManager requestManager = new RequestManager(myRef.getApplicationContext());
        requestManager.requestGetMyStudents(myRef);
    }
    private void setUpSpinner(ArrayList<Estudiante> estudiantes){
        try{

            ArrayList<String> nombres = new ArrayList<>();
            for(Estudiante estudianteFocused : estudiantes){
                nombres.add(estudianteFocused.getNombreCompleto());
                Log.i("Nombre",estudianteFocused.getNombreCompleto());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nombres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDeEstudiantes.setAdapter(adapter);
        }
        catch (Exception e){
            Log.i("error",e.toString());
        }
    }
    public void onResponseGetMyStudents(JSONArray students) {//students = null in error case!!!
        this.estudiantesJsonString = students.toString();

        Gson gson = new Gson();
        Estudiante[] estudianteArray = gson.fromJson(estudiantesJsonString, Estudiante[].class);
        listaEstudiantes = new ArrayList<>(Arrays.asList(estudianteArray));
        for(Estudiante estudiante: listaEstudiantes){
            Log.d("dddd", estudiante.getFirstName());
        }
        this.setUpSpinner(listaEstudiantes);
    }
}
