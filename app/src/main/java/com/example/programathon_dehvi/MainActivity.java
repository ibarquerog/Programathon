package com.example.programathon_dehvi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestManager rm = new RequestManager(this.getApplicationContext());

        rm.requestLogin("1111", )

    }
}
