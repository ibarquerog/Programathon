package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Concretos.Estudiante;

public class CicloDesarrolloIntegral extends AppCompatActivity {
    Estudiante estudiante;
    ArrayList<String>datos;
    private Button btnGoCuestionario;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_desarrollo_integral);
        Intent intent = getIntent();
        datos = intent.getStringArrayListExtra("ESTUDIANTE");
        loadInfo();

        context = this;

        btnGoCuestionario = findViewById(R.id.BTN_Cuestionario_Desarrollo);
        btnGoCuestionario.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, calificarASQ3Activity.class));
            }
        });
    }

    private void loadInfo(){
        TextView nacimiento=findViewById(R.id.text_view_nacimiento);
        TextView dni=findViewById(R.id.text_view_DNI);
        TextView prematuro=findViewById(R.id.text_view_prematuro);
        TextView nombre=findViewById(R.id.text_view_Nombre);
        TextView genero=findViewById(R.id.text_view_genero);
        TextView ingreso=findViewById(R.id.text_view_ingreso);

        nacimiento.setText(datos.get(1));
        dni.setText(datos.get(2));
        prematuro.setText(datos.get(3));
        nombre.setText(datos.get(0));
        genero.setText(datos.get(4));
        ingreso.setText(datos.get(5));
    }
}