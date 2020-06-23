package com.hrishikeshgawas.ekcutting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AttributionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribution);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.attr_recycler_view);
        String[] attrNames = getResources().getStringArray(R.array.attr_name);
        String[] attrDescription = getResources().getStringArray(R.array.attr_desc);
        String[] attrLink = getResources().getStringArray(R.array.attr_link);
        ArrayList<AttributionModel> attrList = new ArrayList<>();
        for(int i =0; i<79; i++){
            attrList.add(new AttributionModel(attrNames[i], attrDescription[i], attrLink[i]));

        }
        recyclerView.setHasFixedSize(true);
        AttributionAdapter attributionAdapter = new AttributionAdapter(this, attrList);
        recyclerView.setAdapter(attributionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private static class AttributionModel {


        String aName;
        String aDesc;
        String aLink;



        private AttributionModel(String aName, String aDesc, String aLink){
            this.aName = aName;
            this.aDesc = aDesc;
            this.aLink= aLink;
        }

        String getaName() {
            return aName;
        }

        String getaDesc() {
            return aDesc;
        }


        String getaLink() {
            return aLink;
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    public static class AttributionAdapter extends RecyclerView.Adapter<AttributionAdapter.MyAttrHolder>{
        Context context;
        ArrayList<AttributionModel> AttrList;
        AttributionAdapter(Context context, ArrayList<AttributionModel> AttrList){
            this.AttrList = AttrList;
            this.context = context;

        }
        @NonNull
        @Override
        public MyAttrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.attribution_item,parent,false);
            return new MyAttrHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAttrHolder holder, int position) {

            holder.attrName.setText(AttrList.get(position).getaName());
            holder.attrDescription.setText(AttrList.get(position).getaDesc());
            holder.attrLink.setText(AttrList.get(position).getaLink());
        }

        @Override
        public int getItemCount() {
            return AttrList.size();
        }

        private static class MyAttrHolder extends RecyclerView.ViewHolder{

            TextView attrName;
            TextView attrDescription;
            TextView attrLink;
            MyAttrHolder(@NonNull View itemView) {
                super(itemView);
                attrName = itemView.findViewById(R.id.attr_name);
                attrDescription = itemView.findViewById(R.id.attr_description);
                attrLink = itemView.findViewById(R.id.attr_link);
            }
        }
    }
}
