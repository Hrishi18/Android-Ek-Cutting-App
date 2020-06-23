package com.hrishikeshgawas.ekcutting;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrishikeshgawas.ekcutting.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favourites extends Fragment {

    private RecyclerView recyclerView;
    private FavDB favDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavouriteAdapter favAdapter;
    private RelativeLayout emptyFLayout;



    public Favourites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        emptyFLayout = root.findViewById(R.id.empty_layout_f);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        favDB = new FavDB(getActivity());


        loadData();
        if (favItemList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyFLayout.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyFLayout.setVisibility(View.GONE);

        }

        return root;
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
                ArrayList<FavItem> myList = new ArrayList<>();
                for(FavItem model : favItemList){
                    String name  = model.getItemSName().toLowerCase();
                    if(name.contains(s)){
                        myList.add(model);

                    }
                }
                if(myList.isEmpty()){
                    Log.e("Veg"," nothing to show hrishi");

                }

                favAdapter.setSearchOperation(myList);


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

    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_NAME));
                String address = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_ADDRESS));
                String description = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_DESCRIPTION));
                String cost = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_COST));
                String link = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_LINK));
                FavItem favItem = new FavItem(name, address, description, cost, id, link);
                favItemList.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter = new FavouriteAdapter(getActivity(), favItemList);

        recyclerView.setAdapter(favAdapter);
    }
}
