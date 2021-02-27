package com.example.itunesapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class appCategoriesAdapter extends RecyclerView.Adapter<appCategoriesAdapter.appCategoriesHolder>{

    ArrayList<String> categories;
    String mtoken;
    AppCategoryAdapter mlistener;
    public appCategoriesAdapter(ArrayList<String> incomingCategories, FragmentActivity activity,String token){
        this.categories=incomingCategories;
        this.mlistener= (AppCategoryAdapter) activity;
        this.mtoken=token;

    }


    @NonNull
    @Override
    public appCategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.appdetailsrecylelist,parent,false);
        appCategoriesHolder appCategoriesHoldervalue = new appCategoriesHolder(view);
        return appCategoriesHoldervalue;
    }

    @Override
    public void onBindViewHolder(@NonNull appCategoriesHolder holder, int position) {


        String category=categories.get(position);
       holder.categoriesValues.setText(category);
        holder.listener=this.mlistener;
        holder.token=this.mtoken;
        holder.categoryName=category;
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public static class appCategoriesHolder extends RecyclerView.ViewHolder{

        TextView categoriesValues;
        View rootView;
        AppCategoryAdapter listener;
        String token;
        String categoryName;
      public appCategoriesHolder(@NonNull View itemView) {
          super(itemView);
          rootView=itemView;
          categoriesValues =itemView.findViewById(R.id.textViewappDetailrecyleList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.toAppList(categoryName,token);
                }
            });



      }
  }

    interface AppCategoryAdapter{
        //void returnToLogin();
        void toAppList(String categoryItem,String token);
    }


}
