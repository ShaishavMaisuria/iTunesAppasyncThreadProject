package com.example.itunesapps;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAccount extends Fragment {


    private static final String ARG_PARAM_REGISTER_ACCOUNT = "ARG_PARAM_REGISTER_ACCOUNT";


    private String rToken;


    public RegisterAccount() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rToken = getArguments().getString(ARG_PARAM_REGISTER_ACCOUNT);

        }
    }

    TextView viewUsername;
    TextView viewEmail;
    TextView viewPassword;
    String name;
    String email;
    String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_account, container, false);

        viewUsername = view.findViewById(R.id.editextPersonNameRegister);
        viewEmail = view.findViewById(R.id.editTextEmailAddressRegister);
        viewPassword = view.findViewById(R.id.editTextPasswordRegister);


        view.findViewById(R.id.buttonSubmitRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = viewUsername.getText().toString();
                email = viewEmail.getText().toString();
                password = viewPassword.getText().toString();
                Log.d("register", "name" + name);
                Log.d("register", "email" + email);
                Log.d("register", "password" + password);


                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(getActivity(), "Password/Email or Password cannot be empty", Toast.LENGTH_SHORT).show();

                } else {


                    DataServices.register(viewUsername.getText().toString(), viewEmail.getText().toString(), viewPassword.getText().toString()
                            , new DataServices.AuthResponse() {
                        @Override
                        public void onSuccess(String token) {
                            mListener.loginIsSuccesful(token);

                        }

                        @Override
                        public void onFailure(DataServices.RequestException exception) {

                        }
                    });

                }
            }
        });


        view.findViewById(R.id.buttonCancelRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.returnToLogin();
            }
        });


        return view;
    }

    RegisterListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.LoginListener) {
            mListener = (RegisterListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement RegisterListener");
        }
    }

    interface RegisterListener {
        void loginIsSuccesful(String token);

        void returnToLogin();
    }

}