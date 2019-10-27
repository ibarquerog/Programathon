package com.example.fundacion_dehvi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

    public void onClickEstudiantes(View view){
        startActivity(new Intent(getApplicationContext(), ConsultarNinos.class));
    }

    public void onClickLogout(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Desea salir de la aplicación?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                LoginData.clearData();
                startActivity(new Intent(ProfesorMenuActivity.this,Login.class));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}