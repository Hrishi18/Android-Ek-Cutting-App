package com.hrishikeshgawas.ekcutting;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NonVegDetailsActivity extends AppCompatActivity {
    RelativeLayout nv_rl_one, nv_rl_two, nv_rl_three, nv_rl_four, nv_rl_five;
    TextView nv_name_one, nv_name_two, nv_name_three, nv_name_four, nv_name_five, nv_address_one, nv_address_two, nv_address_three, nv_address_four, nv_address_five, nv_description_one, nv_description_two, nv_description_three, nv_description_four, nv_description_five, nv_cost_one, nv_cost_two, nv_cost_three, nv_cost_four, nv_cost_five;
    String[] nv_shop_one, nv_shop_two, nv_shop_three, nv_shop_four, nv_shop_five, nv_shop_address_one, nv_shop_address_two, nv_shop_address_three, nv_shop_address_four, nv_shop_address_five, nv_shop_description_one, nv_shop_description_two, nv_shop_description_three, nv_shop_description_four, nv_shop_description_five, nv_shop_cost_one, nv_shop_cost_two, nv_shop_cost_three, nv_shop_cost_four, nv_shop_cost_five, nv_shop_key_id_one, nv_shop_key_id_two, nv_shop_key_id_three, nv_shop_key_id_four, nv_shop_key_id_five, nv_shop_link_one, nv_shop_link_two, nv_shop_link_three, nv_shop_link_four, nv_shop_link_five;
    Button nv_fav_button_one, nv_fav_button_two, nv_fav_button_three, nv_fav_button_four, nv_fav_button_five;
    String  nv_shop_fav_status_one, nv_shop_fav_status_two, nv_shop_fav_status_three, nv_shop_fav_status_four, nv_shop_fav_status_five;
    private FavDB favDB;
    //final ArrayList<DetailsActivity> shopItems = new ArrayList<DetailsActivity>();
    int nvPosition = 0;

    public NonVegDetailsActivity() {
    }

    public String getNVFavStatusOne() {
        return nv_shop_fav_status_one;
    }
    public void setNVFavStatusOne(String nvFavStatusOne) {
        this.nv_shop_fav_status_one = nvFavStatusOne;
    }
    public String getNVFavStatusTwo() {
        return nv_shop_fav_status_two;
    }
    public void setNVFavStatusTwo(String nvFavStatusTwo) {
        this.nv_shop_fav_status_two = nvFavStatusTwo;
    }
    public String getNVFavStatusThree() {
        return nv_shop_fav_status_three;
    }
    public void setNVFavStatusThree(String nvFavStatusThree) {
        this.nv_shop_fav_status_three = nvFavStatusThree;
    }
    public String getNVFavStatusFour() {
        return nv_shop_fav_status_four;
    }
    public void setNVFavStatusFour(String nvFavStatusFour) {
        this.nv_shop_fav_status_four = nvFavStatusFour;
    }
    public String getNVFavStatusFive() {
        return nv_shop_fav_status_five;
    }
    public void setNVFavStatusFive(String nvFavStatusFive) {
        this.nv_shop_fav_status_five = nvFavStatusFive;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nv_details_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        nvPosition = intent.getExtras().getInt("Position");

        favDB = new FavDB(getApplicationContext());
        SharedPreferences   prefs = getApplicationContext().getSharedPreferences("prefs",getApplicationContext().MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }

        nv_name_one = findViewById(R.id.nv_name_one);
        nv_name_two = findViewById(R.id.nv_name_two);
        nv_name_three = findViewById(R.id.nv_name_three);
        nv_name_four = findViewById(R.id.nv_name_four);
        nv_name_five = findViewById(R.id.nv_name_five);

        nv_address_one = findViewById(R.id.nv_address_one);
        nv_address_two = findViewById(R.id.nv_address_two);
        nv_address_three = findViewById(R.id.nv_address_three);
        nv_address_four = findViewById(R.id.nv_address_four);
        nv_address_five = findViewById(R.id.nv_address_five);

        nv_description_one = findViewById(R.id.nv_description_one);
        nv_description_two = findViewById(R.id.nv_description_two);
        nv_description_three = findViewById(R.id.nv_description_three);
        nv_description_four = findViewById(R.id.nv_description_four);
        nv_description_five = findViewById(R.id.nv_description_five);

        nv_cost_one = findViewById(R.id.nv_cost_one);
        nv_cost_two = findViewById(R.id.nv_cost_two);
        nv_cost_three = findViewById(R.id.nv_cost_three);
        nv_cost_four = findViewById(R.id.nv_cost_four);
        nv_cost_five = findViewById(R.id.nv_cost_five);

        nv_fav_button_one = findViewById(R.id.nv_fav_one);
        nv_fav_button_two = findViewById(R.id.nv_fav_two);
        nv_fav_button_three = findViewById(R.id.nv_fav_three);
        nv_fav_button_four = findViewById(R.id.nv_fav_four);
        nv_fav_button_five = findViewById(R.id.nv_fav_five);

        nv_rl_one = findViewById(R.id.nv_rl_one);
        nv_rl_two = findViewById(R.id.nv_rl_two);
        nv_rl_three = findViewById(R.id.nv_rl_three);
        nv_rl_four = findViewById(R.id.nv_rl_four);
        nv_rl_five = findViewById(R.id.nv_rl_five);


        nv_shop_one = getResources().getStringArray(R.array.nv_s_one);
        nv_shop_two = getResources().getStringArray(R.array.nv_s_two);
        nv_shop_three = getResources().getStringArray(R.array.nv_s_three);
        nv_shop_four = getResources().getStringArray(R.array.nv_s_four);
        nv_shop_five = getResources().getStringArray(R.array.nv_s_five);

        nv_shop_address_one = getResources().getStringArray(R.array.nv_s_address_one);
        nv_shop_address_two = getResources().getStringArray(R.array.nv_s_address_two);
        nv_shop_address_three = getResources().getStringArray(R.array.nv_s_address_three);
        nv_shop_address_four = getResources().getStringArray(R.array.nv_s_address_four);
        nv_shop_address_five = getResources().getStringArray(R.array.nv_s_address_five);

        nv_shop_description_one = getResources().getStringArray(R.array.nv_s_disc_one);
        nv_shop_description_two = getResources().getStringArray(R.array.nv_s_disc_two);
        nv_shop_description_three = getResources().getStringArray(R.array.nv_s_disc_three);
        nv_shop_description_four = getResources().getStringArray(R.array.nv_s_disc_four);
        nv_shop_description_five = getResources().getStringArray(R.array.nv_s_disc_five);

        nv_shop_cost_one = getResources().getStringArray(R.array.nv_s_cost_one);
        nv_shop_cost_two = getResources().getStringArray(R.array.nv_s_cost_two);
        nv_shop_cost_three = getResources().getStringArray(R.array.nv_s_cost_three);
        nv_shop_cost_four = getResources().getStringArray(R.array.nv_s_cost_four);
        nv_shop_cost_five = getResources().getStringArray(R.array.nv_s_cost_five);


        nv_shop_key_id_one = getResources().getStringArray(R.array.nv_key_id_one);
        nv_shop_key_id_two = getResources().getStringArray(R.array.nv_key_id_two);
        nv_shop_key_id_three = getResources().getStringArray(R.array.nv_key_id_three);
        nv_shop_key_id_four = getResources().getStringArray(R.array.nv_key_id_four);
        nv_shop_key_id_five = getResources().getStringArray(R.array.nv_key_id_five);

        nv_shop_link_one = getResources().getStringArray(R.array.nv_s_link_one);
        nv_shop_link_two = getResources().getStringArray(R.array.nv_s_link_two);
        nv_shop_link_three = getResources().getStringArray(R.array.nv_s_link_three);
        nv_shop_link_four = getResources().getStringArray(R.array.nv_s_link_four);
        nv_shop_link_five = getResources().getStringArray(R.array.nv_s_link_five);





        nv_name_one.setText(nv_shop_one[nvPosition]);
        nv_name_two.setText(nv_shop_two[nvPosition]);
        nv_name_three.setText(nv_shop_three[nvPosition]);
        nv_name_four.setText(nv_shop_four[nvPosition]);
        nv_name_five.setText(nv_shop_five[nvPosition]);

        nv_address_one.setText(nv_shop_address_one[nvPosition]);
        nv_address_two.setText(nv_shop_address_two[nvPosition]);
        nv_address_three.setText(nv_shop_address_three[nvPosition]);
        nv_address_four.setText(nv_shop_address_four[nvPosition]);
        nv_address_five.setText(nv_shop_address_five[nvPosition]);

        nv_description_one.setText(nv_shop_description_one[nvPosition]);
        nv_description_two.setText(nv_shop_description_two[nvPosition]);
        nv_description_three.setText(nv_shop_description_three[nvPosition]);
        nv_description_four.setText(nv_shop_description_four[nvPosition]);
        nv_description_five.setText(nv_shop_description_five[nvPosition]);

        nv_cost_one.setText(nv_shop_cost_one[nvPosition]);
        nv_cost_two.setText(nv_shop_cost_two[nvPosition]);
        nv_cost_three.setText(nv_shop_cost_three[nvPosition]);
        nv_cost_four.setText(nv_shop_cost_four[nvPosition]);
        nv_cost_five.setText(nv_shop_cost_five[nvPosition]);

        Log.v("NonVegDetailsActivity","fav1 "+ getNVFavStatusOne());
        Log.v("NonVegDetailsActivity","fav2 "+ getNVFavStatusTwo());
        for (int j = 0; j < 50; j++) {
            setNVFavStatusOne("0");
            setNVFavStatusTwo("0");
            setNVFavStatusThree("0");
            setNVFavStatusFour("0");
            setNVFavStatusFive("0");
        }
        Log.v("NonVegDetailsActivity","fav3 "+ getNVFavStatusOne());
        Log.v("NonVegDetailsActivity","fav4 "+ getNVFavStatusTwo());
        readCursorDataOne();
        readCursorDataTwo();
        readCursorDataThree();
        readCursorDataFour();
        readCursorDataFive();


        nv_rl_one.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(nv_shop_link_one[nvPosition]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("NonVegDetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(NonVegDetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }



            }
        });
        nv_rl_two.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(nv_shop_link_two[nvPosition]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("NonVegDetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(NonVegDetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        nv_rl_three.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(nv_shop_link_three[nvPosition]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("NonVegDetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(NonVegDetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        nv_rl_four.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(nv_shop_link_four[nvPosition]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("NonVegDetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(NonVegDetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        nv_rl_five.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(nv_shop_link_five[nvPosition]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("NonVegDetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(NonVegDetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });

        nv_fav_button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("NonVegDetailsActivity","favst "+ getNVFavStatusOne());

                // ShopItem shopItem = sItems.get(position);
                if(getNVFavStatusOne().equals("0")){
                    setNVFavStatusOne("1");
                    favDB.insertIntoTheDatabase(nv_shop_key_id_one[nvPosition],nv_shop_one[nvPosition],nv_shop_address_one[nvPosition],nv_shop_description_one[nvPosition],nv_shop_cost_one[nvPosition],"1",nv_shop_link_one[nvPosition]);
                    nv_fav_button_one.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_one[nvPosition]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    setNVFavStatusOne("0");
                    favDB.remove_fav(nv_shop_key_id_one[nvPosition]);
                    nv_fav_button_one.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_one[nvPosition]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        nv_fav_button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getNVFavStatusTwo().equals("0")){

                    setNVFavStatusTwo("1");
                    favDB.insertIntoTheDatabase(nv_shop_key_id_two[nvPosition],nv_shop_two[nvPosition],nv_shop_address_two[nvPosition],nv_shop_description_two[nvPosition],nv_shop_cost_two[nvPosition],"1",nv_shop_link_two[nvPosition]);
                    nv_fav_button_two.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_two[nvPosition]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    //shopItems.setFavStatus("0");
                    // shopItems.set(position, new DetailsActivity("0"));
                    setNVFavStatusTwo("0");
                    favDB.remove_fav(nv_shop_key_id_two[nvPosition]);
                    nv_fav_button_two.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_two[nvPosition]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        nv_fav_button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getNVFavStatusThree().equals("0")){

                    setNVFavStatusThree("1");
                    favDB.insertIntoTheDatabase(nv_shop_key_id_three[nvPosition],nv_shop_three[nvPosition],nv_shop_address_three[nvPosition],nv_shop_description_three[nvPosition],nv_shop_cost_three[nvPosition],"1",nv_shop_link_three[nvPosition]);
                    nv_fav_button_three.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_three[nvPosition]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    setNVFavStatusThree("0");
                    favDB.remove_fav(nv_shop_key_id_three[nvPosition]);
                    nv_fav_button_three.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_three[nvPosition]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        nv_fav_button_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getNVFavStatusFour().equals("0")){

                    setNVFavStatusFour("1");
                    favDB.insertIntoTheDatabase(nv_shop_key_id_four[nvPosition],nv_shop_four[nvPosition],nv_shop_address_four[nvPosition],nv_shop_description_four[nvPosition],nv_shop_cost_four[nvPosition],"1",nv_shop_link_four[nvPosition]);
                    nv_fav_button_four.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_four[nvPosition]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    setNVFavStatusFour("0");
                    favDB.remove_fav(nv_shop_key_id_four[nvPosition]);
                    nv_fav_button_four.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_four[nvPosition]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        nv_fav_button_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getNVFavStatusFive().equals("0")){
                    setNVFavStatusFive("1");
                    favDB.insertIntoTheDatabase(nv_shop_key_id_five[nvPosition],nv_shop_five[nvPosition],nv_shop_address_five[nvPosition],nv_shop_description_five[nvPosition],nv_shop_cost_five[nvPosition],"1",nv_shop_link_five[nvPosition]);
                    nv_fav_button_five.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_five[nvPosition]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    //shopItems.setFavStatus("0");
                    // shopItems.set(position, new DetailsActivity("0"));
                    setNVFavStatusFive("0");
                    favDB.remove_fav(nv_shop_key_id_five[nvPosition]);
                    nv_fav_button_five.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),nv_shop_five[nvPosition]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });





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


    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    private void readCursorDataOne(){
        Cursor cursor = favDB.read_all_data(nv_shop_key_id_one[nvPosition]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setNVFavStatusOne(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    nv_fav_button_one.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    nv_fav_button_one.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }
    private void readCursorDataTwo(){
        Cursor cursor = favDB.read_all_data(nv_shop_key_id_two[nvPosition]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setNVFavStatusTwo(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    nv_fav_button_two.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    nv_fav_button_two.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataThree(){
        Cursor cursor = favDB.read_all_data(nv_shop_key_id_three[nvPosition]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setNVFavStatusThree(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    nv_fav_button_three.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    nv_fav_button_three.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataFour(){
        Cursor cursor = favDB.read_all_data(nv_shop_key_id_four[nvPosition]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setNVFavStatusFour(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    nv_fav_button_four.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    nv_fav_button_four.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataFive(){
        Cursor cursor = favDB.read_all_data(nv_shop_key_id_five[nvPosition]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setNVFavStatusFive(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    nv_fav_button_five.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    nv_fav_button_five.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }


}
