package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CrearPlanActivity extends AppCompatActivity {

    TextView fechaActualTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_plan);
        fechaActualTA = findViewById(R.id.fechaActualTV);

        // Display a date in day, month, year format
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        fechaActualTA.setText(today);
    }
}
