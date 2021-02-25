package com.example.itunesapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<DataServices.App> {
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.fragment_app_inside_list_view,parent,false);

        }
        DataServices.App app= getItem(position);
        TextView appNameTextView =convertView.findViewById(R.id.textViewAppName);

        TextView artistNameTextView =convertView.findViewById(R.id.textViewArtistName);
        appNameTextView.setText( app.name);
        artistNameTextView.setText( app.artistName);

        return convertView;
    }
}
