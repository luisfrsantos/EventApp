package com.example.eventsapp.events.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.events.model.People
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_people.view.*

class PeopleAdapter(private val peoples: List<People>) :
    RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_people, parent, false)
        )
    }

    override fun getItemCount(): Int = peoples.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val event = peoples[position]
        holder.itemView.apply {
            Picasso.get().load(event.picture)
                .error(R.drawable.ic_launcher_foreground)
                .into(people_imageview)
            events_name.text = event.name
        }
    }

    class PeopleViewHolder(holder: View) : RecyclerView.ViewHolder(holder)
}