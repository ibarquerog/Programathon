package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class calificarASQ3Activity extends AppCompatActivity{
    private calificarASQ3Activity myRef;

    private Spinner sp1, sp2, sp3, sp4, sp5, sp6;
    private int selectedType;
    private JSONObject evaluation = null;
    private Button btnPrev, btnNext, btnPlan, btnCalificar, btnCancelar;
    private ArrayList<String> areaNames;
    private TextView textViewAreaName, txtViewEstado;
    private String studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_asq3);
        areaNames = new ArrayList();
        textViewAreaName = findViewById(R.id.lbl_tipoArea);
        txtViewEstado = findViewById(R.id.txtViewEstado);

        this.selectedType = 0;
        this.myRef = this;

        RequestManager requestManager = new RequestManager(this.getApplicationContext());
        requestManager.requestGetAreas(this);

        sp1 = findViewById(R.id.sp_1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(0, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        sp2 = findViewById(R.id.sp_2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(1, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        sp3 = findViewById(R.id.sp_3);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(2, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        sp4 = findViewById(R.id.sp_4);
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(3, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        sp5 = findViewById(R.id.sp_5);
        sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(4, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        sp6 = findViewById(R.id.sp_6);
        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setJsonValue(5, position * 5);
                getColorStatus(selectedType);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        this.selectedType = 0;

        btnPrev = findViewById(R.id.PrevBtn);
        btnPrev.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedType > 0){
                    selectedType--;
                    setIndexValues(selectedType);
                    getColorStatus(selectedType);
                }
            }
        });

        btnNext = findViewById(R.id.NextBtn);
        btnNext.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedType < 6){
                    selectedType++;
                    setIndexValues(selectedType);
                    getColorStatus(selectedType);
                }
            }
        });

        btnCalificar = findViewById(R.id.registrarBtn);
        btnCalificar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(evaluation != null){
                    RequestManager requestManager = new RequestManager(myRef.getApplicationContext());
                    requestManager.requestAddResults(evaluation, myRef);
                }
            }
        });

        btnPlan = findViewById(R.id.btnCrearPlan);
        btnPlan.setVisibility(View.INVISIBLE);
        btnPlan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnCancelar = findViewById(R.id.cancelBtn);
        btnCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.onBackPressed();
            }
        });
    }

    public int getStatus(int index){
        JSONArray results = this.getResultsOf(index);
        if(results == null){
            return -1;
        }
        else{

            try {

                int sum = 0;
                sum += results.getJSONObject(0).getInt("value");
                sum += results.getJSONObject(1).getInt("value");
                sum += results.getJSONObject(2).getInt("value");
                sum += results.getJSONObject(3).getInt("value");
                sum += results.getJSONObject(4).getInt("value");
                sum += results.getJSONObject(5).getInt("value");

                return sum;

            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    public void setIndexValues(int index){
        JSONArray results = this.getResultsOf(index);
        if(results == null){
            return;
        }
        else{
            this.textViewAreaName.setText(this.areaNames.get(index));
            try {
                sp1.setSelection(results.getJSONObject(0).getInt("value") / 5);
                sp2.setSelection(results.getJSONObject(1).getInt("value") / 5);
                sp3.setSelection(results.getJSONObject(2).getInt("value") / 5);
                sp4.setSelection(results.getJSONObject(3).getInt("value") / 5);
                sp5.setSelection(results.getJSONObject(4).getInt("value") / 5);
                sp6.setSelection(results.getJSONObject(5).getInt("value") / 5);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setJsonValue(int spinnerId, int value){
        JSONArray results = this.getResultsOf(this.selectedType);
        if(results != null){
            try {
                results.getJSONObject(spinnerId).put("value", value);
                Log.i("ddddd", results.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray getResultsOf(int index){
        if(this.evaluation == null){
            return null;
        }
        else{
            try {
                return this.evaluation.getJSONArray("resultList").
                                                            getJSONObject(index).
                                                                getJSONArray("results");

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    public void onGetAreasResult(JSONArray areas){

        if(areas == null){
            return;
        }
        try {
            JSONObject eval = new JSONObject();
            eval.put("attendanceId", 0);
            JSONArray resultList = new JSONArray(), results;
            JSONObject result, evaluation;
            for(int i = 0; i < areas.length(); i++){

                areaNames.add(areas.getJSONObject(i).getString("name"));

                result = new JSONObject();
                results = new JSONArray();


                    result.put("areaId", i);

                    for(int j = 0; j < 6; j++){
                        evaluation = new JSONObject();
                        evaluation.put("id", i);
                        evaluation.put("index", j);
                        evaluation.put("value", 0);
                        results.put(evaluation);
                    }
                    result.put("results", results);

                    resultList.put(result);


            }
            eval.put("resultList", resultList);
            this.evaluation = eval;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onAddResultsResult(boolean result) {
        if(result ){
            Toast.makeText(this.getApplicationContext(), "Se a guardado la evaluacion", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.getApplicationContext(), "Ha ocurrido un error al realizar la evaluacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void getColorStatus(int index){

        int sumResult = this.getStatus(index);
        if(sumResult == -1){
            return;
        }
        else{
            Log.d("ddddddd", sumResult + "   : " + selectedType);
            this.txtViewEstado.setText("Total: " + Integer.toString(sumResult));
            if(this.selectedType == 0 && sumResult >= 0 && sumResult < 35){
                this.txtViewEstado.setBackgroundColor(Color.RED);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 0 && sumResult >= 35 && sumResult < 45){
                this.txtViewEstado.setBackgroundColor(Color.YELLOW);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 0 && sumResult >= 45 && sumResult <= 60){
                this.txtViewEstado.setBackgroundColor(Color.GREEN);
                btnPlan.setVisibility(View.INVISIBLE);
            }
            else if(this.selectedType == 1 && sumResult >= 0 && sumResult < 35){
                this.txtViewEstado.setBackgroundColor(Color.RED);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 1 && sumResult >= 35 && sumResult < 45){
                this.txtViewEstado.setBackgroundColor(Color.YELLOW);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 1 && sumResult >= 45 && sumResult <= 60){
                this.txtViewEstado.setBackgroundColor(Color.GREEN);
                btnPlan.setVisibility(View.INVISIBLE);
            }
            else if(this.selectedType == 2 && sumResult >= 0 && sumResult < 20){
                this.txtViewEstado.setBackgroundColor(Color.RED);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 2 && sumResult >= 20 && sumResult < 35){
                this.txtViewEstado.setBackgroundColor(Color.YELLOW);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 2 && sumResult >= 35 && sumResult <= 60){
                this.txtViewEstado.setBackgroundColor(Color.GREEN);
                btnPlan.setVisibility(View.INVISIBLE);
            }
            else if(this.selectedType == 3 && sumResult >= 0 && sumResult < 35){
                this.txtViewEstado.setBackgroundColor(Color.RED);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 3 && sumResult >= 35 && sumResult < 45){
                this.txtViewEstado.setBackgroundColor(Color.YELLOW);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 3 && sumResult >= 45 && sumResult <= 60){
                this.txtViewEstado.setBackgroundColor(Color.GREEN);
                btnPlan.setVisibility(View.INVISIBLE);
            }
            else if(this.selectedType == 4 && sumResult >= 0 && sumResult < 30){
                this.txtViewEstado.setBackgroundColor(Color.RED);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 4 && sumResult >= 30 && sumResult < 40){
                this.txtViewEstado.setBackgroundColor(Color.YELLOW);
                btnPlan.setVisibility(View.VISIBLE);
            }
            else if(this.selectedType == 4 && sumResult >= 40 && sumResult <= 60){
                this.txtViewEstado.setBackgroundColor(Color.GREEN);
                btnPlan.setVisibility(View.INVISIBLE);
            }

        }
    }
}