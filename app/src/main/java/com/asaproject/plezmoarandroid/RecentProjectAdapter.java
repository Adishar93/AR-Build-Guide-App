package com.asaproject.plezmoarandroid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.asaproject.plezmoarandroid.entities.ModelParts;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecentProjectAdapter extends RecyclerView.Adapter<RecentProjectAdapter.PlaceViewHolder> {


    private ArrayList<KeyForModelKit> modelKitsList;
    private Context context;

    public RecentProjectAdapter(ArrayList<KeyForModelKit> modelKitsList, Context context) {
        this.context = context;
        this.modelKitsList = modelKitsList;

    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_custom_layout,viewGroup);
//                .inflate(R.layout.recyclerview_custom_layout,false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, final int i) {
        ModelKit mi=modelKitsList.get(i).mi;
       // placeViewHolder.textTitle.setText(ei.getTitle());

        Glide.with(context).load(mi.getLinkMainImg()).into(placeViewHolder.mPlace);
        //Glide.get(context).load(mi.getLinkMainImg()).transform(new RoundedCornersTransformation(43,0)).into(placeViewHolder.mPlace);
        placeViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){

                Intent s = new Intent(context,InfoActivity.class);
                s.putExtra("ModelId",modelKitsList.get(i).key);
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

        public PlaceViewHolder(View itemView) {
            super(itemView);

            mPlace = itemView.findViewById(R.id.ivPlace);
        }
    }
}


