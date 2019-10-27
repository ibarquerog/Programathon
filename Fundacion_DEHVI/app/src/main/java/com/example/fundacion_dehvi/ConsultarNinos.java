package com.example.fundacion_dehvi;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import Concretos.Estudiante;

public class ConsultarNinos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ninos);
        setupRecyclerView();
    }
    private void setupRecyclerView(){
        try{

            final ConsultarNinos activity=this;
            ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
            estudiantes.add(new Estudiante("Ignacio"));
            estudiantes.add(new Estudiante("Paul"));
            estudiantes.add(new Estudiante("Feng"));
            estudiantes.add(new Estudiante("Daniel"));

            RecyclerView recyclerView = findViewById(R.id.recyclerViewConsultarNinos);
            MyListAdapter adapter = new MyListAdapter(estudiantes);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        }
        catch (Exception e){
            Log.i("errorWTF",e.toString());
        }
    }
}
