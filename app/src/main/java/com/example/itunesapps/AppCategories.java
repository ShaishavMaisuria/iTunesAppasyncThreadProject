package com.example.itunesapps;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AppCategories extends Fragment {


    private static final String ARG_PARAM_APP_CATEGORIES = "ARG_PARAM_APP_CATEGORIES";



    private String atoken;


    public AppCategories() {
        // Required empty public constructor
    }

    public static AppCategories newInstance(String token) {
        AppCategories fragment = new AppCategories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_APP_CATEGORIES,token);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            atoken = getArguments().getString(ARG_PARAM_APP_CATEGORIES);

        }
    }



    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> categories= new ArrayList<>();
    TextView textViewGreetings;
    String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("App Categories");

        Log.d("demo", "onCreateView: AppCategories" );


        View view= inflater.inflate(R.layout.fragment_appcat, container, false);
        listView=view.findViewById(R.id.listView);


    DataServices.getAppCategories(atoken
            , new DataServices.DataResponse<String>() {
                @Override
                public void onSuccess(ArrayList<String> data) {
                    categories.clear();
                    categories.addAll(data);
                adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,categories);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.d("AppCategories","item"+adapter.getItem(position) );

                        mListener.toAppList(adapter.getItem(position),atoken );



                    }
                });
                }

                @Override
                public void onFailure(DataServices.RequestException exception) {

                }
            });
        textViewGreetings= view.findViewById(R.id.textViewGreetings);


        DataServices.getAccount(atoken, new DataServices.AccountResponse() {
            @Override
            public void onSuccess(DataServices.Account account) {
                Log.d("AppCategories",account.toString());
                username=account.getName();
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {

            }
        });
       textViewGreetings.setText("Welcome" + username);



       view.findViewById(R.id.buttonLogOutAppcat).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mListener.returnToLogin();

           }
       });
        return view;
    }
    AppCategory mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginFragment.LoginListener){
            mListener=(AppCategory)context;
        }else{
            throw new RuntimeException(context.toString()+"must implement loginListener");
        }
    }

    interface AppCategory{
        void returnToLogin();
        void toAppList(String categoryItem,String token);
    }


}