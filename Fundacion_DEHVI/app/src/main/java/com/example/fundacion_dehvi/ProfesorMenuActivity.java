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
    private ProfesorMenuActivity myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_menu);

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