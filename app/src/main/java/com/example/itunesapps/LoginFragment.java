package com.example.itunesapps;
/*
@author shaishav
 */
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";


    private String mParam1;


    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    EditText userEmail;
    EditText userPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);


        userEmail = view.findViewById(R.id.editTextEmailAddressLogin);
        userPassword = view.findViewById(R.id.editTextPasswordLogin);


        view.findViewById(R.id.buttonLoginFragmentLogin).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email=userEmail.getText().toString();
                        String password=userPassword.getText().toString();


                        InputMethodManager imm= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                        if(email.isEmpty() || password.isEmpty()){
                            Toast.makeText(getActivity(),"Password/Email cannot be empty",Toast.LENGTH_SHORT).show();

                        }

                         DataServices.login(email, password, new DataServices.AuthResponse() {
                             @Override
                             public void onSuccess(String token) {

                                mListener.loginIsSuccesful(token);

                             }

                             @Override
                             public void onFailure(DataServices.RequestException exception) {
                                 Toast.makeText(getActivity(),"Unsuccessfull Logina",Toast.LENGTH_SHORT).show();
                             }
                         });


                    }
                }
        );


        view.findViewById(R.id.buttonCreateNewAccountLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoRegistration();
            }
        });



        return view;
    }

    LoginListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginListener){
            mListener=(LoginListener)context;
        }else{
            throw new RuntimeException(context.toString()+"must implement loginListener");
        }
    }

    interface LoginListener{
        void loginIsSuccesful(String token);
        void gotoRegistration();
        }



}