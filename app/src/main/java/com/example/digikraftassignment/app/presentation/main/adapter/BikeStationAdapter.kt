package com.example.digikraftassignment.app.presentation.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.digikraftassignment.R
import com.example.digikraftassignment.app.domain.model.DFeatures
import com.google.android.gms.maps.model.LatLng

class BikeStationAdapter(
    private val context: Context,
    private val currentLatLng: LatLng,
    private val bikeStationsList: MutableList<DFeatures>?,
    private val onClickItem: (DFeatures) -> Unit
) : RecyclerView.Adapter<BikeStationAdapter.BikeStationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeStationViewHolder {
        return BikeStationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bike_station, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BikeStationViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = bikeStationsList?.size ?: 0

    inner class BikeStationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val clStationBase = itemView.findViewById<ConstraintLayout>(R.id.cl_station_base)
        private val txtStationName = itemView.findViewById<TextView>(R.id.tv_bike_station_name)
        private val txtDistance = itemView.findViewById<TextView>(R.id.tv_bike_station_distance)
        private val txtBikeStation = itemView.findViewById<TextView>(R.id.tv_title_bike_station)
        private val txtAvailableBikes = itemView.findViewById<TextView>(R.id.tv_available_bikes)
        private val txtAvailablePlaces = itemView.findViewById<TextView>(R.id.tv_available_places)

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int) {

            val bikeStation = bikeStationsList?.get(position)!!

            txtStationName.text =
                bikeStation.id.toString().plus(" ${bikeStation.bikeStationProperties?.lable}")
            txtDistance.text = getDistance(bikeStation).toString().plus("m")
            txtBikeStation.text = context.getString(R.string.label_bike_station)
            txtAvailableBikes.text = bikeStation.bikeStationProperties?.bikes
            txtAvailablePlaces.text = bikeStation.bikeStationProperties?.freeRacks

            clStationBase.setOnClickListener { onClickItem(bikeStation) }

        }

        private fun getDistance(bikeStation: DFeatures) : Float {
            val startPoint = Location("userLocation")
            startPoint.latitude = currentLatLng.latitude
            startPoint.longitude = currentLatLng.longitude

            val endPoint = Location("bikeStationLocation")
            endPoint.latitude = bikeStation.geometry?.coordinates?.get(0)!!
            endPoint.longitude = bikeStation.geometry?.coordinates?.get(1)!!

            return startPoint.distanceTo(endPoint)
        }
    }
}