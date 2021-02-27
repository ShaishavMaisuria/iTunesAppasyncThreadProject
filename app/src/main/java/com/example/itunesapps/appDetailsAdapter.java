package com.example.itunesapps;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class appDetailsAdapter  extends RecyclerView.Adapter<appDetailsAdapter.AppDetailsHolder> {

ArrayList<String> genre;

    public appDetailsAdapter(ArrayList<String> incomingGenre, FragmentActivity activity){
        this.genre=incomingGenre;
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

        String myApp=genre.get(position);
        holder.genreList.setText(myApp);

        Log.d("appDetailsAdapter",myApp.toString());


    }


    @Override

    public int getItemCount() {

        return this.genre.size();
    }




    public static class AppDetailsHolder extends RecyclerView.ViewHolder{

        TextView genreList;
        View rootView;
        int position;


        public AppDetailsHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            genreList =itemView.findViewById(R.id.textViewappDetailrecyleList);



        }


    }



}
