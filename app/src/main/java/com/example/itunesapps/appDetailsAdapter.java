package com.example.itunesapps;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class appDetailsAdapter  extends RecyclerView.Adapter<appDetailsAdapter.AppDetailsHolder> {

ArrayList<DataServices.App> app;

    public appDetailsAdapter(ArrayList<DataServices.App> incomingApp,FragmentActivity activity){
        this.app=incomingApp;
    }


    @NonNull
    @Override
    public AppDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.appdetailsrecylelist,parent,false);
        AppDetailsHolder appDetailsHolder = new AppDetailsHolder(view);
        return appDetailsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppDetailsHolder holder, int position){

        DataServices.App myApp=app.get(position);
        holder.appNameTextView.setText(myApp.name);
        holder.artistNameTextView.setText(myApp.artistName);
        holder.releaseDateTextView.setText(myApp.releaseDate);
        holder.position=position;
        holder.iapp=myApp;
        holder.Listener=this.mListener;

    }


    @Override

    public int getItemCount() {

        return this.app.size();
    }




    public static class AppDetailsHolder extends RecyclerView.ViewHolder{

        public AppDetailsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}
