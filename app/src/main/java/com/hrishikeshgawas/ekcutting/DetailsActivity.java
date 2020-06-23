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

public class DetailsActivity extends AppCompatActivity {
    RelativeLayout rl_one, rl_two, rl_three, rl_four, rl_five;
    TextView name_one, name_two, name_three, name_four, name_five, address_one, address_two, address_three, address_four, address_five, description_one, description_two, description_three, description_four, description_five, cost_one, cost_two, cost_three, cost_four, cost_five;
    String[] shop_one, shop_two, shop_three, shop_four, shop_five, shop_address_one, shop_address_two, shop_address_three, shop_address_four, shop_address_five, shop_description_one, shop_description_two, shop_description_three, shop_description_four, shop_description_five, shop_cost_one, shop_cost_two, shop_cost_three, shop_cost_four, shop_cost_five, shop_key_id_one, shop_key_id_two ,shop_key_id_three,shop_key_id_four, shop_key_id_five, shop_link_one, shop_link_two, shop_link_three, shop_link_four, shop_link_five;
    Button fav_button_one, fav_button_two, fav_button_three, fav_button_four, fav_button_five;
    String  shop_fav_status_one, shop_fav_status_two, shop_fav_status_three, shop_fav_status_four, shop_fav_status_five;
    private FavDB favDB;
    //final ArrayList<DetailsActivity> shopItems = new ArrayList<DetailsActivity>();
    int position = 0;

    public DetailsActivity() {
        }

    public String getFavStatusOne() {
        return shop_fav_status_one;
    }
    public void setFavStatusOne(String favStatusOne) {
        this.shop_fav_status_one = favStatusOne;
    }
    public String getFavStatusTwo() {
        return shop_fav_status_two;
    }
    public void setFavStatusTwo(String favStatusTwo) {
        this.shop_fav_status_two = favStatusTwo;
    }
    public String getFavStatusThree() {
        return shop_fav_status_three;
    }
    public void setFavStatusThree(String favStatusThree) {
        this.shop_fav_status_three = favStatusThree;
    }
    public String getFavStatusFour() {
        return shop_fav_status_four;
    }
    public void setFavStatusFour(String favStatusFour) {
        this.shop_fav_status_four = favStatusFour;
    }
    public String getFavStatusFive() {
        return shop_fav_status_five;
    }
    public void setFavStatusFive(String favStatusFive) {
        this.shop_fav_status_five = favStatusFive;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");

        favDB = new FavDB(getApplicationContext());
        SharedPreferences   prefs = getApplicationContext().getSharedPreferences("prefs",getApplicationContext().MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }

        name_one = findViewById(R.id.name_one);
        name_two = findViewById(R.id.name_two);
        name_three = findViewById(R.id.name_three);
        name_four = findViewById(R.id.name_four);
        name_five = findViewById(R.id.name_five);

        address_one = findViewById(R.id.address_one);
        address_two = findViewById(R.id.address_two);
        address_three = findViewById(R.id.address_three);
        address_four = findViewById(R.id.address_four);
        address_five = findViewById(R.id.address_five);

        description_one = findViewById(R.id.description_one);
        description_two = findViewById(R.id.description_two);
        description_three = findViewById(R.id.description_three);
        description_four = findViewById(R.id.description_four);
        description_five = findViewById(R.id.description_five);

        cost_one = findViewById(R.id.cost_one);
        cost_two = findViewById(R.id.cost_two);
        cost_three = findViewById(R.id.cost_three);
        cost_four = findViewById(R.id.cost_four);
        cost_five = findViewById(R.id.cost_five);

        fav_button_one = findViewById(R.id.fav_one);
        fav_button_two = findViewById(R.id.fav_two);
        fav_button_three = findViewById(R.id.fav_three);
        fav_button_four = findViewById(R.id.fav_four);
        fav_button_five = findViewById(R.id.fav_five);

        rl_one = findViewById(R.id.rl_one);
        rl_two = findViewById(R.id.rl_two);
        rl_three = findViewById(R.id.rl_three);
        rl_four = findViewById(R.id.rl_four);
        rl_five = findViewById(R.id.rl_five);


        shop_one = getResources().getStringArray(R.array.s_one);
        shop_two = getResources().getStringArray(R.array.s_two);
        shop_three = getResources().getStringArray(R.array.s_three);
        shop_four = getResources().getStringArray(R.array.s_four);
        shop_five = getResources().getStringArray(R.array.s_five);

        shop_address_one = getResources().getStringArray(R.array.s_address_one);
        shop_address_two = getResources().getStringArray(R.array.s_address_two);
        shop_address_three = getResources().getStringArray(R.array.s_address_three);
        shop_address_four = getResources().getStringArray(R.array.s_address_four);
        shop_address_five = getResources().getStringArray(R.array.s_address_five);

        shop_description_one = getResources().getStringArray(R.array.s_disc_one);
        shop_description_two = getResources().getStringArray(R.array.s_disc_two);
        shop_description_three = getResources().getStringArray(R.array.s_disc_three);
        shop_description_four = getResources().getStringArray(R.array.s_disc_four);
        shop_description_five = getResources().getStringArray(R.array.s_disc_five);

        shop_cost_one = getResources().getStringArray(R.array.s_cost_one);
        shop_cost_two = getResources().getStringArray(R.array.s_cost_two);
        shop_cost_three = getResources().getStringArray(R.array.s_cost_three);
        shop_cost_four = getResources().getStringArray(R.array.s_cost_four);
        shop_cost_five = getResources().getStringArray(R.array.s_cost_five);


        shop_key_id_one = getResources().getStringArray(R.array.key_id_one);
        shop_key_id_two = getResources().getStringArray(R.array.key_id_two);
        shop_key_id_three = getResources().getStringArray(R.array.key_id_three);
        shop_key_id_four = getResources().getStringArray(R.array.key_id_four);
        shop_key_id_five = getResources().getStringArray(R.array.key_id_five);

        shop_link_one = getResources().getStringArray(R.array.s_link_one);
        shop_link_two = getResources().getStringArray(R.array.s_link_two);
        shop_link_three = getResources().getStringArray(R.array.s_link_three);
        shop_link_four = getResources().getStringArray(R.array.s_link_four);
        shop_link_five = getResources().getStringArray(R.array.s_link_five);





        name_one.setText(shop_one[position]);
        name_two.setText(shop_two[position]);
        name_three.setText(shop_three[position]);
        name_four.setText(shop_four[position]);
        name_five.setText(shop_five[position]);

        address_one.setText(shop_address_one[position]);
        address_two.setText(shop_address_two[position]);
        address_three.setText(shop_address_three[position]);
        address_four.setText(shop_address_four[position]);
        address_five.setText(shop_address_five[position]);

        description_one.setText(shop_description_one[position]);
        description_two.setText(shop_description_two[position]);
        description_three.setText(shop_description_three[position]);
        description_four.setText(shop_description_four[position]);
        description_five.setText(shop_description_five[position]);

        cost_one.setText(shop_cost_one[position]);
        cost_two.setText(shop_cost_two[position]);
        cost_three.setText(shop_cost_three[position]);
        cost_four.setText(shop_cost_four[position]);
        cost_five.setText(shop_cost_five[position]);



        for (int j = 0; j < 50; j++) {
            setFavStatusOne("0");
            setFavStatusTwo("0");
            setFavStatusThree("0");
            setFavStatusFour("0");
            setFavStatusFive("0");
        }
        readCursorDataOne();
        readCursorDataTwo();
        readCursorDataThree();
        readCursorDataFour();
        readCursorDataFive();

        rl_one.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(shop_link_one[position]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(DetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }



            }
        });
        rl_two.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(shop_link_two[position]));
                     startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(DetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        rl_three.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(shop_link_three[position]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(DetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        rl_four.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(shop_link_four[position]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(DetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });
        rl_five.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(shop_link_five[position]));
                    startActivity(intent);
                }catch (NullPointerException e){
                    Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                    Toast.makeText(DetailsActivity.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                }


            }
        });

        fav_button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // ShopItem shopItem = sItems.get(position);
              if(getFavStatusOne().equals("0")){
                    setFavStatusOne("1");
                    favDB.insertIntoTheDatabase(shop_key_id_one[position],shop_one[position],shop_address_one[position],shop_description_one[position],shop_cost_one[position],"1",shop_link_one[position]);
                    fav_button_one.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),shop_one[position]+" is added to favourites", Toast.LENGTH_SHORT).show();

              }else {
                  setFavStatusOne("0");
                  favDB.remove_fav(shop_key_id_one[position]);
                  fav_button_one.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                  Toast.makeText(getApplicationContext(),shop_one[position]+" is removed from favourites", Toast.LENGTH_SHORT).show();


              }
            }
        });

        fav_button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getFavStatusTwo().equals("0")){

                    setFavStatusTwo("1");
                    favDB.insertIntoTheDatabase(shop_key_id_two[position],shop_two[position],shop_address_two[position],shop_description_two[position],shop_cost_two[position],"1",shop_link_two[position]);
                    fav_button_two.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),shop_two[position]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    //shopItems.setFavStatus("0");
                    // shopItems.set(position, new DetailsActivity("0"));
                    setFavStatusTwo("0");
                    favDB.remove_fav(shop_key_id_two[position]);
                    fav_button_two.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),shop_two[position]+" is removed from favourites", Toast.LENGTH_SHORT).show();


                }
            }
        });

        fav_button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getFavStatusThree().equals("0")){

                    setFavStatusThree("1");
                    favDB.insertIntoTheDatabase(shop_key_id_three[position],shop_three[position],shop_address_three[position],shop_description_three[position],shop_cost_three[position],"1",shop_link_three[position]);
                    fav_button_three.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),shop_three[position]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                     setFavStatusThree("0");
                    favDB.remove_fav(shop_key_id_three[position]);
                    fav_button_three.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),shop_three[position]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        fav_button_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getFavStatusFour().equals("0")){

                    setFavStatusFour("1");
                    favDB.insertIntoTheDatabase(shop_key_id_four[position],shop_four[position],shop_address_four[position],shop_description_four[position],shop_cost_four[position],"1",shop_link_four[position]);
                    fav_button_four.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),shop_four[position]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    setFavStatusFour("0");
                    favDB.remove_fav(shop_key_id_four[position]);
                    fav_button_four.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),shop_four[position]+" is removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });

        fav_button_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ShopItem shopItem = sItems.get(position);
                if(getFavStatusFive().equals("0")){
                    setFavStatusFive("1");
                    favDB.insertIntoTheDatabase(shop_key_id_five[position],shop_five[position],shop_address_five[position],shop_description_five[position],shop_cost_five[position],"1",shop_link_five[position]);
                    fav_button_five.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    Toast.makeText(getApplicationContext(),shop_five[position]+" is added to favourites", Toast.LENGTH_SHORT).show();

                }else {
                    //shopItems.setFavStatus("0");
                    // shopItems.set(position, new DetailsActivity("0"));
                    setFavStatusFive("0");
                    favDB.remove_fav(shop_key_id_five[position]);
                    fav_button_five.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    Toast.makeText(getApplicationContext(),shop_five[position]+" is removed from favourites", Toast.LENGTH_SHORT).show();
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
        Cursor cursor = favDB.read_all_data(shop_key_id_one[position]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setFavStatusOne(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    fav_button_one.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    fav_button_one.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }
    private void readCursorDataTwo(){
        Cursor cursor = favDB.read_all_data(shop_key_id_two[position]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setFavStatusTwo(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    fav_button_two.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    fav_button_two.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataThree(){
        Cursor cursor = favDB.read_all_data(shop_key_id_three[position]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setFavStatusThree(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    fav_button_three.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    fav_button_three.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataFour(){
        Cursor cursor = favDB.read_all_data(shop_key_id_four[position]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setFavStatusFour(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    fav_button_four.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    fav_button_four.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }

    private void readCursorDataFive(){
        Cursor cursor = favDB.read_all_data(shop_key_id_five[position]);
        SQLiteDatabase db  =  favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                setFavStatusFive(item_fav_status);
                if(item_fav_status!= null && item_fav_status.equals("1")){
                    fav_button_five.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                }else if(item_fav_status!= null && item_fav_status.equals("0")){
                    fav_button_five.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }

            }


        }finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
            }
        }
    }


}
