package com.example.itunesapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, AppCategories.AppCategory , RegisterAccount.RegisterListener, AppListFragment.appListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new LoginFragment())
                .commit();
    }

String cToken;
    @Override
    public void loginIsSuccesful(String token) {
        cToken=token;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,AppCategories.newInstance(cToken),"appCatogeries")
                .commit();

    }

    @Override
    public void gotoRegistration() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new RegisterAccount())
                .commit();

    }


    @Override
    public void returnToLogin() {
        cToken="";
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new LoginFragment())
                .commit();
    }
String categoryItemValue;
    @Override
    public void toAppList(String categoryItem, String token) {
        this.categoryItemValue= categoryItem;
        this.cToken=token;
    getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,AppListFragment.newInstance(this.categoryItemValue, this.cToken),"AppListFragment")
            .addToBackStack("AppListFragment")
                .commit();

    }

DataServices.App mApp;
    @Override
    public void goToAppDetails(DataServices.App application) {
mApp=application;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,AppDetails.newInstance(mApp),"AppDetails")
                .addToBackStack("AppDetails")
                .commit();
    }
}