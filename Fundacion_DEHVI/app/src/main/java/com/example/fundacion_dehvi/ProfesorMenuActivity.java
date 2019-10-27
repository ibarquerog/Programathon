package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfesorMenuActivity extends AppCompatActivity {

    private ProfesorMenuActivity myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_menu);

        this.myRef = this;

        RequestManager requestManager = new RequestManager(myRef.getApplicationContext());
        requestManager.requestGetMyStudents(myRef);
        requestManager.requestGetFormByName("2 Meses ASQ-3");
    }

    public void onResponseGetMyStudents(JSONArray students) {//students = null in error case!!!
        return;
    }
}
