package com.kathleenwang.animalcrossingfish

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.list_item.view.*

class FishAdapter(val context: Context, val fishes: List<Fish>) :
    RecyclerView.Adapter<FishAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = fishes[position]
        holder.bind(restaurant)
    }

    override fun getItemCount() = fishes.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fish: Fish) {
            itemView.rvName.text = fish.name
            itemView.rvPrice.text = "${fish.price} bells"
            itemView.rvLocation.text = fish.availability.location
            itemView.rvRarity.text = fish.availability.rarity

            when (fish.availability.rarity) {
                "Common" -> itemView.rvRarity.setTextColor(ContextCompat.getColor(
                    context,
                    R.color.purple_500
                ))
                "Uncommon" -> itemView.rvRarity.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.orange
                    )
                )
                "Rare" -> itemView.rvRarity.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                "Ultra-rare" -> itemView.rvRarity.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.purple_700
                    )
                )

            }
            Glide.with(context).load(fish.imageSrc)
                .fitCenter()
                .transform(RoundedCornersTransformation(20, 5))
                .into(itemView.imageView)

        }

    }
}

