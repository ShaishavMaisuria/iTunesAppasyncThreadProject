package com.example.itunesapps;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class AppDetails extends Fragment {

    private static final String ARG_PARAM_APPDETAILS = "ARG_PARAM_APPDETAILS";


   DataServices.App mApp;


    public AppDetails() {
        // Required empty public constructor
    }


    public static AppDetails newInstance(DataServices.App App) {
        AppDetails fragment = new AppDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_APPDETAILS, App);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mApp = (DataServices.App)getArguments().getSerializable(ARG_PARAM_APPDETAILS);

        }
    }
    TextView artistName;
    TextView appName;
    TextView releaseDate;
    //ListView listView;

    ArrayAdapter<String> adapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_app_details, container, false);

        getActivity().setTitle("App Details");

        artistName=view.findViewById(R.id.textViewArtistNameAppDetails);
        appName=view.findViewById(R.id.textViewAppNameAppDetails);
        releaseDate=view.findViewById(R.id.textViewReleaseDateAppDetails);

        artistName.setText(mApp.artistName);
        appName.setText(mApp.name);
        releaseDate.setText(mApp.releaseDate);

//        recyclerView=view.findViewById(R.id.recyclerViewAppDetails);
//        recyclerView.setHasFixedSize(true);
//        layoutManager=new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//
//        adapter =new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,mApp.genres);

        //recyclerView.setAdapter(adapter);

        return view;
    }





}