package com.example.itunesapps;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AppListFragment extends Fragment {


    private static final String ARG_PARAM_APP_LIST_CATEGORY_ITEM = "ARG_PARAM_APP_LIST_CATEGORY_ITEM";
    private static final String ARG_PARAM_APP_LIST_TOKEN = "ARG_PARAM_APP_LIST_TOKEN";

    private String categoryItem;
    private String lToken;

    public AppListFragment() {
        // Required empty public constructor
    }


    public static AppListFragment newInstance(String categoryItemValue, String token) {
        AppListFragment fragment = new AppListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_APP_LIST_CATEGORY_ITEM, categoryItemValue);
        args.putString(ARG_PARAM_APP_LIST_TOKEN, token);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryItem = getArguments().getString(ARG_PARAM_APP_LIST_CATEGORY_ITEM);
            lToken = getArguments().getString(ARG_PARAM_APP_LIST_TOKEN);


        }
    }

    // ListView applistView;
    // ArrayAdapter<DataServices.App> adapter;
    ArrayList<DataServices.App> appListCategories = new ArrayList<>();
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    UserAdapterRecylerView adapter;

    //UserAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);
        getActivity().setTitle(categoryItem);


        recyclerView = view.findViewById(R.id.recycleViewAppList);


        DataServices.getAppsByCategory(lToken, categoryItem, new DataServices.DataResponse<DataServices.App>() {
            @Override
            public void onSuccess(ArrayList<DataServices.App> data) {

                appListCategories.addAll(data);


                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new UserAdapterRecylerView(data,getActivity() );


                //adapter= new ArrayAdapter<DataServices.App>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,appListCategories);
                recyclerView.setAdapter(adapter);
                Log.d("applist", "adapter list:" + appListCategories.toString());


            }

            @Override
            public void onFailure(DataServices.RequestException exception) {

            }
        });

        return view;
    }

    appListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof appListListener) {
            mListener = (appListListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement loginListener");
        }
    }

    interface appListListener {
        void goToAppDetails(DataServices.App application);

    }
}