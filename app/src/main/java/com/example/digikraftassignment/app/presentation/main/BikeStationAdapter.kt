package com.example.digikraftassignment.app.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.digikraftassignment.R
import com.example.digikraftassignment.core.extension.showToast
import com.example.digikraftassignment.core.general.GoTo

class BikeStationAdapter(
    private val context: Context,
    private val onClickItem: (String) -> Unit
) : RecyclerView.Adapter<BikeStationAdapter.BikeStationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeStationViewHolder {
        return BikeStationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bike_station, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BikeStationViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 10

    inner class BikeStationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val clStationBase = itemView.findViewById<ConstraintLayout>(R.id.cl_station_base)
        private val txtStationName = itemView.findViewById<TextView>(R.id.tv_bike_station_name)
        private val txtDistance = itemView.findViewById<TextView>(R.id.tv_bike_station_distance)
        private val txtBikeStation = itemView.findViewById<TextView>(R.id.tv_title_bike_station)
        private val txtAvailableBikes = itemView.findViewById<TextView>(R.id.tv_available_bikes)
        private val txtAvailablePlaces = itemView.findViewById<TextView>(R.id.tv_available_places)

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int) {

            txtStationName.text = "15589 Ofiar Dabia"
            txtDistance.text = "800m"
            txtBikeStation.text = context.getString(R.string.label_bike_station)
            txtAvailableBikes.text = "7"
            txtAvailablePlaces.text = "21"

            clStationBase.setOnClickListener { onClickItem("Click") }

        }
    }
}