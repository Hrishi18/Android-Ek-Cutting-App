package com.hrishikeshgawas.ekcutting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private Context context;
    private List<FavItem> favItemList;
    private FavDB favDB;


    public FavouriteAdapter(Context context, List<FavItem> favItemList) {
        this.context = context;
        this.favItemList = favItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,
                parent, false);
        favDB = new FavDB(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.favName.setText(favItemList.get(position).getItemSName());
        holder.favDescription.setText(favItemList.get(position).getItemDescription());
        holder.favAddress.setText(favItemList.get(position).getItemAddress());
        holder.favCost.setText(favItemList.get(position).getItemCost());
        int kid = Integer.parseInt(favItemList.get(position).getKey_id());
        if(0<=kid && kid<=47){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg1));
        }else if (50<=kid && kid<=97){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg2));
        }else if (100<=kid && kid<=147){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg3));
        }else if (150<=kid && kid<=197){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg4));
        }else if (200<=kid && kid<=247){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg5));
        }else if (250<=kid && kid<=262){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg1));
        }else if (270<=kid && kid<=282){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg2));
        }else if (290<=kid && kid<=302){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg3));
        }else if (310<=kid && kid<=322){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg4));
        }else if (330<=kid && kid<=342){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg5));
        }else if (500<=kid && kid<=599){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorNVeg3));
        }else if (600<=kid && kid<=914){
            holder.dsgnBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorVeg3));
        }
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout favRelativeLayout;
        TextView favName, favDescription, favAddress, favCost;
        Button favBtn, dsgnBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favName = itemView.findViewById(R.id.name);
            favDescription = itemView.findViewById(R.id.description);
            favAddress = itemView.findViewById(R.id.address);
            favCost = itemView.findViewById(R.id.cost);
            favBtn = itemView.findViewById(R.id.fav);
            dsgnBtn = itemView.findViewById(R.id.but);
            favRelativeLayout = itemView.findViewById(R.id.f_rl);

            favRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    FavItem favItem = favItemList.get(pos);
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(favItem.getLink()));
                        context.startActivity(intent);
                    }catch (NullPointerException e){
                        Log.e("DetailsActivity", "onClick: NullPointerException: Couldn't open map."+ e.getMessage());
                        Toast.makeText(context, "Couldn't open map", Toast.LENGTH_SHORT).show();
                    }
                }
            });







            //remove from fav after click
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);

                    favDB.remove_fav(favItem.getKey_id());
                    Toast.makeText(context,favItem.getItemSName()+" is removed from favourites", Toast.LENGTH_SHORT).show();
                    removeItem(position);


                }
            });
        }
    }

    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favItemList.size());
    }



    public void setSearchOperation(ArrayList<FavItem> newList){
        favItemList = new ArrayList<>();
        favItemList.addAll(newList);
        notifyDataSetChanged();

    }
}
