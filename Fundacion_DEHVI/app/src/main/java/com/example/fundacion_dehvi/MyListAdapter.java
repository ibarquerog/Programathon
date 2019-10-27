package com.example.fundacion_dehvi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fundacion_dehvi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Concretos.Estudiante;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<Estudiante> listdata;
    private Context current;
    private ArrayList<String> aq3 = new ArrayList<String>();

    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<Estudiante> listdata, Context current) {
        this.current = current;
    }

    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<Estudiante> listdata) throws ParseException {
        this.listdata = listdata;
        //calculateMonths();
    }
    private void calculateMonths() throws ParseException {
        for(Estudiante es:listdata) {
            Date current = new Date();
            String cDate = new SimpleDateFormat("yyyy/MM/dd").format(current);
            String dob=es.getFormattedDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date a = format.parse(cDate);
            Date b=format.parse(dob);
            long dif=getMonthDifference(a,b);
            aq3.add(getAQ3(dif));
        }
    }
    private String getAQ3(long dif){
        if(dif>=1 && dif<3){ return "2 Meses ASQ-3";}
        else if(dif>=3 && dif<5){ return "4 Meses ASQ-3";}
        else if(dif>=5 && dif<7){ return "6 Meses ASQ-3";}
        else if(dif>=7 && dif<9){ return "8 Meses ASQ-3";}
        else if(dif>=9 && dif<10){ return "9 Meses ASQ-3";}
        else if(dif>=10 && dif<11){ return "10 Meses ASQ-3";}
        else if(dif>=11 && dif<13){ return "12 Meses ASQ-3";}
        else if(dif>=13 && dif<15){ return "14 Meses ASQ-3";}
        else if(dif>=15 && dif<17){ return "16 Meses ASQ-3";}
        else if(dif>=17 && dif<19){ return "18 Meses ASQ-3";}
        else if(dif>=19 && dif<21){ return "20 Meses ASQ-3";}
        else if(dif>=21 && dif<23){ return "22 Meses ASQ-3";}
        else if(dif>=23 && dif<25){ return "24 Meses ASQ-3";}
        else if(dif>=25 && dif<28){ return "27 Meses ASQ-3";}
        else if(dif>=28 && dif<31){ return "30 Meses ASQ-3";}
        else if(dif>=31 && dif<34){ return "33 Meses ASQ-3";}
        else if(dif>=34 && dif<39){ return "36 Meses ASQ-3";}
        else if(dif>=39 && dif<45){ return "42 Meses ASQ-3";}
        else if(dif>=45 && dif<51){ return "48 Meses ASQ-3";}
        else if(dif>=51 && dif<57){ return "54 Meses ASQ-3";}
        else if(dif>=57 && dif<66){ return "30 Meses ASQ-3";}
        else {
            return "";
        }



    }

    private long getMonthDifference(Date a, Date b){
        long different = a.getTime() - b.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long mothsInMilli = daysInMilli * 30;
        long elapsedMonths = different / mothsInMilli;
        return elapsedMonths;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Estudiante estudiante = listdata.get(position);
        holder.textView.setText(listdata.get(position).getFirstName()+" "+listdata.get(position).getLastName());
        holder.asq3.setText(aq3.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(current, calificarASQ3Activity.class);
                intent.putExtra("ID", Integer.toString(estudiante.getId()));
                current.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public TextView asq3;
        public ViewHolder(View itemView) {
            super(itemView);
            this.asq3=(TextView) itemView.findViewById(R.id.recycler_view_item_ASQ3);
            asq3.setGravity(Gravity.RIGHT);
            this.textView = (TextView) itemView.findViewById(R.id.recycler_view_item_full_name);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativelayout);
        }
    }
}  