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
public class NonVeg extends Fragment {


    private ArrayList<nonVegFoodModel> nonVegFoodItemInfo;
    private RecyclerView recyclerView;
    private NonVegGridAdapter adapter;

    public NonVeg() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_veg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.rvNonVeg);
        String[] nonVegFoodItemNames = getResources().getStringArray(R.array.n_v_f_names);
        int[] nonVegFoodItemNumber = getResources().getIntArray(R.array.n_v_f_number);
        TypedArray nImgs = getResources().obtainTypedArray(R.array.n_v_f_images);

        nonVegFoodItemInfo = new ArrayList<>();


        //nonVegFoodItemInfo.clear();
        for(int i =0; i<13; i++){
            nonVegFoodItemInfo.add(new nonVegFoodModel(nonVegFoodItemNumber[i],nImgs.getResourceId(i, -1), nonVegFoodItemNames[i]));

        }


        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new NonVegGridAdapter(getActivity(), nonVegFoodItemInfo);
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
                ArrayList<nonVegFoodModel> myList = new ArrayList<>();
                for(nonVegFoodModel nModel : nonVegFoodItemInfo){
                    String nName  = nModel.getNonVegFoodName().toLowerCase();
                    if(nName.contains(s)){
                        myList.add(nModel);
                        Log.e("NonVeg"," data search2 "+nModel);
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



    private class nonVegFoodModel implements Serializable {
        int nonVegFoodItemNumber;
        int nonVegFoodImageId;
        String nonVegFoodName;



        private nonVegFoodModel(int nonVegFoodItemNumber, int nonVegFoodImageId, String nonVegFoodName){
            this.nonVegFoodItemNumber = nonVegFoodItemNumber;
            this.nonVegFoodImageId = nonVegFoodImageId;
            this.nonVegFoodName= nonVegFoodName;
        }


        public int getNonVegFoodItemNumber() {
            return nonVegFoodItemNumber;
        }

        public void setNonVegFoodItemNumber(int nonVegFoodItemNumber) {
            this.nonVegFoodItemNumber = nonVegFoodItemNumber;
        }

        public int getNonVegFoodImageId() {
            return nonVegFoodImageId;
        }

        public void setNonVegFoodImageId(int nonVegFoodImageId) {
            this.nonVegFoodImageId = nonVegFoodImageId;
        }

        public String getNonVegFoodName() {
            return nonVegFoodName;
        }

        public void setNonVegFoodName(String nonVegFoodName) {
            this.nonVegFoodName = nonVegFoodName;
        }



    }

    public class NonVegGridAdapter extends RecyclerView.Adapter<NonVegGridAdapter.GridHolder>{

        Context context;

        ArrayList<nonVegFoodModel> nonVegFoodItemInfo;


        public NonVegGridAdapter(Context context, ArrayList<nonVegFoodModel> nonVegFoodItemInfo) {

            this.nonVegFoodItemInfo = nonVegFoodItemInfo;
            this.context = context;
        }




        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.non_veg_item, parent, false);
            return new GridHolder(view);
        }

        @Override
        public void onBindViewHolder(GridHolder holder, final int position) {
            final nonVegFoodModel nonVegFoodModels = nonVegFoodItemInfo.get(position);
            holder.imageView.requestLayout();
            holder.imageView.setImageResource(nonVegFoodItemInfo.get(position).getNonVegFoodImageId());
            holder.textView.setText(nonVegFoodItemInfo.get(position).getNonVegFoodName());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nonVegFoodModel nonVegFoodModels = nonVegFoodItemInfo.get(position);
                    Intent intent = new Intent(getActivity(), NonVegDetailsActivity.class);
                    intent.putExtra("Position", nonVegFoodModels.getNonVegFoodItemNumber());
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return nonVegFoodItemInfo.size();
        }



        private class GridHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            CardView cardView;


            public GridHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.ivNImage);
                textView = itemView.findViewById(R.id.tvNCaption);
                cardView = itemView.findViewById(R.id.myNCardView);
            }



        }
        public void setSearchOperation(ArrayList<nonVegFoodModel> newList){
            nonVegFoodItemInfo = new ArrayList<>();
            nonVegFoodItemInfo.addAll(newList);
            notifyDataSetChanged();

        }




    }

}
