package com.example.fundacion_dehvi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fundacion_dehvi.R;

import java.util.ArrayList;

import Concretos.Estudiante;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<Estudiante> listdata;
    private Context current;

    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<Estudiante> listdata, Context current) {
        this.current = current;
        this.listdata = listdata;
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
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.recycler_view_item_full_name);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativelayout);
        }
    }
}  