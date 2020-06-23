
package com.hrishikeshgawas.ekcutting;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Explore extends Fragment {

    private ArrayList<shopModel> eShopInfo;





    private String[] e_shop, e_shop_address, e_shop_description, e_shop_cost, e_lat, e_lon, e_shop_key_id, e_shop_link;
    private int[]  e_shop_v_nv;
    private double dist;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private RelativeLayout emptyRelativeLayout;




    public Explore() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        e_shop = getResources().getStringArray(R.array.e_s);
        e_shop_address = getResources().getStringArray(R.array.e_s_address);
        e_shop_description = getResources().getStringArray(R.array.e_s_disc);
        e_shop_cost = getResources().getStringArray(R.array.e_s_cost);
        e_lat = getResources().getStringArray(R.array.lat);
        e_lon = getResources().getStringArray(R.array.lon);
        e_shop_v_nv = getResources().getIntArray(R.array.e_shop_v_nv);
        e_shop_key_id = getResources().getStringArray(R.array.e_key_id);
        e_shop_link = getResources().getStringArray(R.array.e_s_link);

        recyclerView = getView().findViewById(R.id.recycler_view);
        emptyRelativeLayout = getView().findViewById(R.id.empty_layout);
        eShopInfo = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), eShopInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        eShopInfo.clear();
        Log.v("Explore", "ClrmSG " + eShopInfo);
        if(MainActivity.getLat()==0 && MainActivity.getLon()==0){
            Toast.makeText(getActivity(),"Please allow location permission from settings and restart the app.",Toast.LENGTH_LONG).show();
        }



        for(int i =0; i<414; i++){
            double lat1 = Double.parseDouble(e_lat[i]);
            double lon1 = Double.parseDouble(e_lon[i]);
            distanceCalc(MainActivity.getLat(),MainActivity.getLon(), lat1, lon1);
            //float e_d = (float)dist;
            if(dist<1000){
                eShopInfo.add(new shopModel(e_shop[i],e_shop_address[i],e_shop_description[i],e_shop_cost[i],e_shop_v_nv[i],e_shop_key_id[i],"0",e_shop_link[i],String.valueOf(dist)));
            }
        }

        if (eShopInfo.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyRelativeLayout.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyRelativeLayout.setVisibility(View.GONE);

        }










        swipeRefreshLayout = getView().findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                eShopInfo.clear();
                swipeRefreshLayout.setRefreshing(true);

                for(int i =0; i<414; i++){
                    double lat1 = Double.parseDouble(e_lat[i]);
                    double lon1 = Double.parseDouble(e_lon[i]);
                    distanceCalc(MainActivity.getLat(),MainActivity.getLon(), lat1, lon1);
                    //float e_d = (float)dist;
                    if(dist<1000){
                        eShopInfo.add(new shopModel(e_shop[i],e_shop_address[i],e_shop_description[i],e_shop_cost[i],e_shop_v_nv[i],e_shop_key_id[i],"0",e_shop_link[i],String.valueOf(dist)));
                    }
                }
                adapter.notifyDataSetChanged();
                if (eShopInfo.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    emptyRelativeLayout.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyRelativeLayout.setVisibility(View.GONE);

                }

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Log.v("DetailsActivity", "msg1 " + shopItems.getFavStatus());

    }




    public void distanceCalc(double lat1, double lon1, double lat2, double lon2) {
        dist = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(lat1);
        locationA.setLongitude(lon1);
        Location locationB = new Location("B");
        locationB.setLatitude(lat2);
        locationB.setLongitude(lon2);
        dist = (locationA.distanceTo(locationB));
    }
   /* private double distanceCalc(double lat1, double lon1, double lat2, double lon2) {



        // haversine great circle distance approximation, returns meters
        double theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of seperation
        dist = dist * 1852; // 1852 meters per nautical mile


        // textLatLong.setText(dist);

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }*/









}
