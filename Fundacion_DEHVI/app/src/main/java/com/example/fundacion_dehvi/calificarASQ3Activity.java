package com.example.fundacion_dehvi;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button btnPrev, btnNext;

    public void asd(){
        Log.i("dddd", "asdsadaddsa");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_asq3);

        //RequestManager requestManager = new RequestManager(this.getApplicationContext());
        //requestManager.requestGetAreas(this);

        sp1 = findViewById(R.id.sp_1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                asd();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                asd();
            }
        });

        sp2 = findViewById(R.id.sp_2);

        sp3 = findViewById(R.id.sp_3);

        sp4 = findViewById(R.id.sp_4);

        sp5 = findViewById(R.id.sp_5);

        sp6 = findViewById(R.id.sp_6);


        this.selectedType = 0;

        btnPrev = findViewById(R.id.PrevBtn);
        btnPrev.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedType > 0){
                    selectedType--;
                    setIndexValues(selectedType);
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
                }
            }
        });
    }

    public void setIndexValues(int index){
        JSONArray results = this.getResultsOf(index);
        if(results == null){
            return;
        }
        else{
            try {
                sp1.setSelection(results.getJSONObject(index).getInt("value") / 5);
                sp2.setSelection(results.getJSONObject(index).getInt("value") / 5);
                sp3.setSelection(results.getJSONObject(index).getInt("value") / 5);
                sp4.setSelection(results.getJSONObject(index).getInt("value") / 5);
                sp5.setSelection(results.getJSONObject(index).getInt("value") / 5);
                sp6.setSelection(results.getJSONObject(index).getInt("value") / 5);
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
            //eval.put("attendanceId", value)-----------------------
            JSONArray resultList = new JSONArray(), results;
            JSONObject result, evaluation;
            for(int i = 0; i < areas.length(); i++){

                result = new JSONObject();
                results = new JSONArray();


                    result.put("areaId", Integer.toString(i));

                    for(int j = 0; j < 6; j++){
                        evaluation = new JSONObject();
                        evaluation.put("id", Integer.toString(j));
                        evaluation.put("index", Integer.toString(j));
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
        Toast.makeText(this.getApplicationContext(), evaluation.toString(), Toast.LENGTH_LONG).show();
    }

}