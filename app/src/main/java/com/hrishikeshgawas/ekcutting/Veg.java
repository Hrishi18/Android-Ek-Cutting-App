package com.hrishikeshgawas.ekcutting;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import android.widget.TextView;

import com.hrishikeshgawas.ekcutting.R;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Veg extends Fragment {


    private ArrayList<vegFoodModel> vegFoodItemInfo;
    private RecyclerView recyclerView;
    private VegGridAdapter adapter;

    public Veg() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veg, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.rvVeg);
        String[] vegFoodItemNames = getResources().getStringArray(R.array.v_f_names);
        int[] vegFoodItemNumber = getResources().getIntArray(R.array.v_f_number);
        TypedArray imgs = getResources().obtainTypedArray(R.array.v_f_images);
        //Bitmap[] bitmaps = setUpBitmaps();
        vegFoodItemInfo = new ArrayList<>();
        //vegFoodItemInfo.clear();

        for(int i =0; i<48; i++){
            vegFoodItemInfo.add(new vegFoodModel(vegFoodItemNumber[i],imgs.getResourceId(i, -1), vegFoodItemNames[i]));

        }


        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new VegGridAdapter(getActivity(), vegFoodItemInfo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                 s = s.toLowerCase();
                 ArrayList<vegFoodModel> myList = new ArrayList<>();
                 for(vegFoodModel model : vegFoodItemInfo){
                     String name  = model.getVegFoodName().toLowerCase();
                     if(name.contains(s)){
                         myList.add(model);
                         Log.e("Veg"," data search2 "+model);
                     }
                 }
                 if(myList.isEmpty()){
                     Log.e("Veg"," nothing to show hrishi");

                 }
                Log.e("Veg"," data search3 "+myList);
                 adapter.setSearchOperation(myList);

                Log.e("Veg"," data search1 "+s);
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }



    private class vegFoodModel implements Serializable {
        int vegFoodItemNumber;
        int vegFoodImageId;
        String vegFoodName;



        private vegFoodModel(int vegFoodItemNumber, int vegFoodImageId, String vegFoodName){
            this.vegFoodItemNumber = vegFoodItemNumber;
            this.vegFoodImageId = vegFoodImageId;
            this.vegFoodName= vegFoodName;
        }


        public int getVegFoodItemNumber() {
            return vegFoodItemNumber;
        }

        public void setVegFoodItemNumber(int vegFoodItemNumber) {
            this.vegFoodItemNumber = vegFoodItemNumber;
        }

        public int getVegFoodImageId() {
            return vegFoodImageId;
        }

        public void setVegFoodImageId(int vegFoodImageId) {
            this.vegFoodImageId = vegFoodImageId;
        }

        public String getVegFoodName() {
            return vegFoodName;
        }

        public void setVegFoodName(String vegFoodName) {
            this.vegFoodName = vegFoodName;
        }



    }

    public class VegGridAdapter extends RecyclerView.Adapter<VegGridAdapter.GridHolder>{

        Context context;

        ArrayList<vegFoodModel> vegFoodItemInfo;


        public VegGridAdapter(Context context, ArrayList<vegFoodModel> vegFoodItemInfo) {

            this.vegFoodItemInfo = vegFoodItemInfo;
            //this.vegFoodItemInfoFiltered = vegFoodItemInfo;
            this.context = context;
        }




        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.veg_item, parent, false);
            return new GridHolder(view);
        }

        @Override
        public void onBindViewHolder(GridHolder holder, final int position) {
            final vegFoodModel vegFoodModels = vegFoodItemInfo.get(position);
            holder.imageView.requestLayout();
            holder.imageView.setImageResource(vegFoodItemInfo.get(position).getVegFoodImageId());
            holder.textView.setText(vegFoodItemInfo.get(position).getVegFoodName());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vegFoodModel vegFoodModels = vegFoodItemInfo.get(position);
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("Position", vegFoodModels.getVegFoodItemNumber());
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return vegFoodItemInfo.size();
        }



        private class GridHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            CardView cardView;


            public GridHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.ivImage);
                textView = itemView.findViewById(R.id.tvCaption);
                cardView = itemView.findViewById(R.id.myCardView);
            }



        }
        public void setSearchOperation(ArrayList<vegFoodModel> newList){
            vegFoodItemInfo = new ArrayList<>();
            vegFoodItemInfo.addAll(newList);
            notifyDataSetChanged();

        }




    }













}
