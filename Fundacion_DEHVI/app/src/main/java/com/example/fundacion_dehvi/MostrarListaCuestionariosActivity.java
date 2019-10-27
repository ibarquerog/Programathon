package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

public class MostrarListaCuestionariosActivity extends AppCompatActivity {

    Spinner spinnerasq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista_cuestionarios);

        spinnerasq = findViewById(R.id.spinnerasq3);
        spinnerasq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
