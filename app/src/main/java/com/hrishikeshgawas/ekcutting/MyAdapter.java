package com.hrishikeshgawas.ekcutting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    Context context;
    private FavDB favDB;


    private ArrayList<shopModel> eShopInfo;

    public MyAdapter(Context context, ArrayList<shopModel> eShopInfo) {

        this.eShopInfo = eShopInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        favDB = new FavDB(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs",context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final shopModel shopModels = eShopInfo.get(position);


        holder.eSName.setText(eShopInfo.get(position).getEName());
        holder.eSAddress.setText(eShopInfo.get(position).getEAddress());
        holder.eSDescription.setText(eShopInfo.get(position).getEDescription());
        holder.eSCost.setText(eShopInfo.get(position).getECost());

        BigDecimal b1 = new BigDecimal(Float.parseFloat(eShopInfo.get(position).getEDist())/1000);
        MathContext m = new MathContext(3); // 3 precision
        // b1 is rounded using m
        BigDecimal b2 = b1.round(m);
        String a = "~";
        String b = String.valueOf(b2);
        String c = " Km";
        String d = a + b + c;
        holder.eSDist.setText(d);
        if(eShopInfo.get(position).getEVNv() == 0) {
            holder.eBut.setBackgroundResource(R.color.colorNVeg3);
        }else{
            holder.eBut.setBackgroundResource(R.color.colorVeg3);
        }

        if(eShopInfo.get(position).getEFavStatus() == "0") {
            holder.e_fav_button.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
        }else{
            holder.e_fav_button.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        }
        readCursorData(shopModels,holder);



    }

    @Override
    public int getItemCount() {
        return eShopInfo.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView eSName, eSAddress, eSDescription, eSCost, eSDist;
        public Button eBut, e_fav_button;
        public RelativeLayout erelativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eSName = itemView.findViewById(R.id.e_name);
            eSAddress =itemView.findViewById(R.id.e_address);
            eSDescription = itemView.findViewById(R.id.e_description);
            eSCost = itemView.findViewById(R.id.e_cost);
            eBut = itemView.findViewById(R.id.e_but);
            eSDist = itemView.findViewById(R.id.e_dist);
            e_fav_button = itemView.findViewById(R.id.e_fav);
            erelativeLayout = itemView.findViewById(R.id.e_rl);

            erelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    shopModel shopModels = eShopInfo.get(pos);
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(shopModels.getELink()));
                        context.startActivity(intent);
                    }catch (NullPointerException e){
                        Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                        Toast.makeText(context, "Couldn't open map", Toast.LENGTH_SHORT).show();
                    }
                }
            });



            e_fav_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    shopModel shopModels = eShopInfo.get(pos);

                    // ShopItem shopItem = sItems.get(position);
                    if(shopModels.getEFavStatus().equals("0")){
                        Log.v("MyAdapter","pos "+ pos);

                        shopModels.setEFavStatus("1");
                        favDB.insertIntoTheDatabase(shopModels.getEKeyId(),shopModels.getEName(),shopModels.getEAddress(),shopModels.getEDescription(),shopModels.getECost(),shopModels.getEFavStatus(),shopModels.getELink());
                        Log.v("MyAdapter","FavS "+ shopModels.getEFavStatus());

                        e_fav_button.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                        Toast.makeText(context,shopModels.getEName()+" is added to favourites", Toast.LENGTH_SHORT).show();

                    }else {
                        //shopItems.setFavStatus("0");
                        // shopItems.set(position, new DetailsActivity("0"));
                        shopModels.setEFavStatus("0");
                        favDB.remove_fav(shopModels.getEKeyId());
                        e_fav_button.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                        Toast.makeText(context,shopModels.getEName()+" is removed from favourites", Toast.LENGTH_SHORT).show();

                    }





                }
            });

        }
    }
    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences prefs = context.getSharedPreferences("prefs",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    private void readCursorData(shopModel shopModel, MyViewHolder myViewHolder){
        Cursor cursor = favDB.read_all_data(shopModel.getEKeyId());
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                shopModel.setEFavStatus(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    myViewHolder.e_fav_button.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    myViewHolder.e_fav_button.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }
}











































/*
    private List<distanceModel> objectList;

    private LayoutInflater inflater;
    private static double dist;

    public MyAdapter(Context context, List<distanceModel>objectList){
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.explore_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {

        return objectList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        distanceModel current = objectList.get(position);

        holder.setData(current, position);




    }


class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView distance;
    private ImageView imgThumb;
    private TextView e_name, e_address, e_description, e_cost;
    private Button e_but;
    private int position;
    private distanceModel currentObject;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        //distance      = (TextView)  itemView.findViewById(R.id.address);
        // imgThumb    = (ImageView) itemView.findViewById(R.id.img_view);

        e_name = itemView.findViewById(R.id.e_name);
        e_address = itemView.findViewById(R.id.e_address);
        e_description = itemView.findViewById(R.id.e_description);
        e_cost = itemView.findViewById(R.id.e_cost);
        e_but = itemView.findViewById(R.id.e_but);

    }

    public void setData(distanceModel currentObject, int position) {

        // this.imgThumb.setImageResource(currentObject.getImageId());

        Log.v("Explore", "msg 111 " + position);




        this.position = position;
        this.currentObject = currentObject;
        this.e_name.setText(Explore.e_shop[position]);

        this.e_address.setText(Explore.e_shop_address[position]);
        this.e_description.setText(Explore.e_shop_description[position]);
        this.e_cost.setText(Explore.e_shop_cost[position]);
        Log.v("Explore", "msg sp " + position);
        //Log.v("Explore","msg dsp "+ dist);
        Log.v("Explore", "msg s16 " + Explore.e_shop[position]);


    }

}*/
