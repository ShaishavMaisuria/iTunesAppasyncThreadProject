package com.example.itunesapps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapterRecylerView extends RecyclerView.Adapter<UserAdapterRecylerView.UserViewHolder> {

    ArrayList<DataServices.App> app;
    userAdapterListener mListener;

    public UserAdapterRecylerView(ArrayList<DataServices.App> incomingApp, FragmentActivity activity){
        this.app=incomingApp;
        this.mListener= (userAdapterListener) activity;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_app_inside_list_view,parent,false);
        UserViewHolder  userViewHolder= new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

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

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView appNameTextView ;
        TextView artistNameTextView ;
        TextView releaseDateTextView;
        View rootView;
        int position;
        DataServices.App iapp;
        userAdapterListener Listener;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            appNameTextView =itemView.findViewById(R.id.textViewAppName);
            artistNameTextView =itemView.findViewById(R.id.textViewArtistName);
            releaseDateTextView =itemView.findViewById(R.id.textViewReleaseDate);

           ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Log.d("UserAdapter","item Clicked" +position+ " user " +iapp.artistName);
                       // mListener.goToAppDetails(iapp);

                    Listener.goToAppDetails(iapp);
                }
            });

        }
    }




//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//
//        if (recyclerView instanceof userAdapterListener) {
//            mListener = (userAdapterListener) recyclerView;
//        } else {
//            throw new RuntimeException(recyclerView.toString() + "must implement loginListener");
//        }
//    }


    public interface userAdapterListener {
        void goToAppDetails(DataServices.App application);

    }
}
