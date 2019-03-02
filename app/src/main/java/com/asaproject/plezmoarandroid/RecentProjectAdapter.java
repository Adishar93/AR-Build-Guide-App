package com.asaproject.plezmoarandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.asaproject.plezmoarandroid.entities.ModelParts;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecentProjectAdapter extends RecyclerView.Adapter<RecentProjectAdapter.PlaceViewHolder> {


    private ArrayList<ModelKit> modelKitsList;
    private Context context;

    public RecentProjectAdapter(ArrayList<ModelKit> modelKitsList, Context context) {
        this.context = context;
        this.modelKitsList = modelKitsList;

    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_custom_layout,null);
//                .inflate(R.layout.recyclerview_custom_layout,false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, final int i) {
        ModelKit mi=modelKitsList.get(i);
       // placeViewHolder.textTitle.setText(ei.getTitle());
placeViewHolder.recentprojecttitile.setText(mi.getName());
        Glide.with(context).load(mi.getLinkMainImg()).into(placeViewHolder.mPlace);
        //Glide.get(context).load(mi.getLinkMainImg()).transform(new RoundedCornersTransformation(43,0)).into(placeViewHolder.mPlace);
        placeViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){

                Intent s = new Intent(context,InfoActivity.class);
                s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                s.putExtra("ModelId",modelKitsList.get(i).getId());
                context.startActivity(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelKitsList.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        ImageView mPlace;
        TextView recentprojecttitile;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            mPlace = itemView.findViewById(R.id.ivPlace);
            recentprojecttitile = itemView.findViewById(R.id.tvRecentProjectTitle);
//            Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
//            Typeface typeface2 = Typeface.createFromAsset(getAssets(),"font/roboto_regular.ttf");
//            recentprojecttitile.setTypeface(typeface2);

        }
    }
}


