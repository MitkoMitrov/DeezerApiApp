package com.example.deezerapiapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.deezerapiapp.R
import com.example.deezerapiapp.models.Data

class TracksAdapter(var allTracks: MutableList<Data>): RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val trackId: TextView
        val trackTitle: TextView

        init{
            trackId = view.findViewById(R.id.textViewTrackId)
            trackTitle = view.findViewById(R.id.textViewTrackTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.track, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTrack = allTracks[position]
        holder.trackId.text = currentTrack.id.toString()
        holder.trackTitle.text = currentTrack.title
    }

    override fun getItemCount(): Int {
        return allTracks.size
    }

    fun updateData(data: MutableList<Data>){
        this.allTracks = data
        this.notifyDataSetChanged()
    }

}