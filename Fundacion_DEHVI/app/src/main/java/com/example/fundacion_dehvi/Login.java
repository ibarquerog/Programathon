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
        editTextUser=findViewById(R.id.EditText_Login_User);
        editTextPass=findViewById(R.id.EditText_Login_Password);
        this.MyRef = this;

        if(!this.isNetworkConnected()){
            Toast.makeText(this.MyRef.getApplicationContext(), "El dispositivo no tiene conexión a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private boolean validateInputFields(){

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
        }
        return true;
    }

    public void onClickBtnLogin(View view){

        if(validateInputFields()){
            RequestManager rm = new RequestManager(this.getApplicationContext());
            //LoginData.accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxMjk1ODIsImV4cCI6MTU3MjEzMzE4MiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMDAxL2F1dGhzZXJ2ZXIiLCJhdWQiOlsiaHR0cDovL2xvY2FsaG9zdDozMDAxL2F1dGhzZXJ2ZXIvcmVzb3VyY2VzIiwiRGVodmlBUEkiXSwiY2xpZW50X2lkIjoiZGVodmlfYXBpIiwic3ViIjoiNSIsImF1dGhfdGltZSI6MTU3MjEyOTU4MiwiaWRwIjoibG9jYWwiLCJnaXZlbl9uYW1lIjoiSm9yZ2UgQXJpYXMiLCJlbWFpbCI6ImpvcmdlYXJpYXNAZ21haWwuY29tIiwicm9sZSI6IjUiLCJ6b25laW5mbyI6IjEiLCJzY29wZSI6WyJlbWFpbCIsIm9wZW5pZCIsInJvbGUiLCJhcGkucmVzb3VyY2UiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.Y-aAk05kIMglkcueHvI9cn-6C4OHx_cjKaw5us2NISH1dhfJ6uZpa0WmP_1Q83EVNTjUsPIKW8RugktCifTJegwknqEKPv594OCr0Hkg8Z8vBJfjG8Ja9nBHWlEH80NJ-E_0v1TvL76haXBK3vPqHtUO2s2_xPNHZP96_0kPpQwSzIC6ZHSKBOA47sjlr3wnR23dgOjqGVun7rnGqdeabI5NoOHJxE2k-HYdc7Dxat41PD-9FYYfEiorIoeCcFHONfFVnxvgOX67PeiNR785s5ZdC8e6REpRst1fokK5hbKa-CtyugRQgFhfGumMtsloDgv6XVOtlMXT4uc2jPordg";
            //rm.requestGetTeacherByDNI();
            rm.requestLogin(this.editTextUser.getText().toString(), this.editTextPass.getText().toString(), this.MyRef);
            //rm.requestLogin(this.editTextUser.getText().toString(), "Te$t1234", this.MyRef);
            //rm.requestLogin("1111", "Te$t1234", this.MyRef);
        }

    }

    public void onRequestLoginResponse(String countType){

        switch(countType){
            case "":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                return;
            case "Profesor":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario de profesor", Toast.LENGTH_SHORT).show();
                break;
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
        startActivity(new Intent(Login.this,MainActivity.class));
    }
}
