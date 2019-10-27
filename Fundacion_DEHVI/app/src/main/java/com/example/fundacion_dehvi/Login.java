package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText editTextUser, editTextPass;
    private Login MyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUser = findViewById(R.id.EditText_Login_User);
        editTextPass = findViewById(R.id.EditText_Login_Password);
        this.MyRef = this;

        if(!this.isNetworkConnected()){
            Toast.makeText(this.MyRef.getApplicationContext(), "El dispositivo no tiene conexión a internet", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private boolean validateInputFields(){
        /*
        if(!this.isNetworkConnected()){
            Toast.makeText(this.MyRef.getApplicationContext(), "El dispositivo no tiene conexión a internet", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editTextPass.getText().toString().equals("") || editTextUser.getText().toString().equals("")){
            Toast.makeText(this.MyRef.getApplicationContext(), "Complete todos los espacios", Toast.LENGTH_SHORT).show();
            return false;
        }
       else if(editTextPass.getText().toString().length() <8){
            Toast.makeText(this.MyRef.getApplicationContext(), "Contraseña debe tener mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }

    public void onClickBtnLogin(View view){

        if(validateInputFields()){
            RequestManager rm = new RequestManager(this.getApplicationContext());
            rm.requestLogin(this.editTextUser.getText().toString(), this.editTextPass.getText().toString(), this.MyRef);
            //rm.requestLogin(this.editTextUser.getText().toString(), "Te$t1234", this.MyRef);
            rm.requestLogin("1111", "Te$t1234", this.MyRef);
        }

    }

    public void onRequestLoginResponse(String countType){

        switch(countType){
            case "":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                return;
            case "Profesor":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario de profesor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, ProfesorMenuActivity.class));
                return;
            case "Estudiante":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario de Estudiante", Toast.LENGTH_SHORT).show();
                break;
            case "Administrador":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario de Administrador", Toast.LENGTH_SHORT).show();
                break;
            case "Super":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario de Super", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(new Intent(Login.this, MainActivity.class));
    }
}