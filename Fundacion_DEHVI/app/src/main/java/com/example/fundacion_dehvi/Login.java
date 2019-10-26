package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private boolean validateInputFields(){
        EditText editTextUser=findViewById(R.id.EditText_Login_User);
        EditText editTextPass=findViewById(R.id.EditText_Login_Password);

        if(editTextPass.getText().toString().equals("") && editTextUser.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    public void onClickBtnLogin(View view){
        if(validateInputFields()){

        }
        else {
            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
