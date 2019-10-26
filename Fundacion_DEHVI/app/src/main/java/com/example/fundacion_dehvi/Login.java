package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }

    private boolean validateInputFields(){
        if(editTextPass.getText().toString().equals("") && editTextUser.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    public void onClickBtnLogin(View view){

        if(validateInputFields()){
            RequestManager rm = new RequestManager(this.getApplicationContext());
            rm.requestLogin(this.editTextUser.getText().toString(), this.editTextPass.getText().toString(), this.MyRef);
            //rm.requestLogin("1111", "Te$t1234", this.MyRef);
        }
    }

    public void onRequestLoginResponse(String countType){

        switch(countType){
            case "":
                Toast.makeText(this.MyRef.getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                break;
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
    }
}
