package com.example.fundacion_dehvi;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import Concretos.Estudiante;

public class ConsultarNinos extends AppCompatActivity {

    private ConsultarNinos myRef;
    public ArrayList<Estudiante> listaEstudiantes;
    private String estudiantesJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ninos);
        this.myRef = this;
        RequestManager requestManager = new RequestManager(myRef.getApplicationContext());
        requestManager.requestGetMyStudents(myRef);

    }
    private void setupRecyclerView(ArrayList<Estudiante> estudiantes){
        try{

            RecyclerView recyclerView = findViewById(R.id.recyclerViewConsultarNinos);
            MyListAdapter adapter = new MyListAdapter(estudiantes, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        }
        catch (Exception e){
            Log.i("error",e.toString());
        }
    }

    public void onResponseGetMyStudents(JSONArray students) {//students = null in error case!!!
        try {
            this.estudiantesJsonString = students.toString();
            Gson gson = new Gson();
            Estudiante[] estudianteArray = gson.fromJson(estudiantesJsonString, Estudiante[].class);
            listaEstudiantes = new ArrayList<>(Arrays.asList(estudianteArray));
            this.setupRecyclerView(listaEstudiantes);
        }
        catch (Exception e){
            Toast.makeText(this, "Usted no tiene estudiantes asignados", Toast.LENGTH_LONG).show();
        }
    }
}
