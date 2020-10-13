package com.example.eventsapp.events.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.commons.helper.DateHelper
import com.example.eventsapp.commons.helper.Money
import com.example.eventsapp.events.model.Events
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_events.view.*

class EventsAdapter(private val events: List<Events>, private val itemSelected: ItemSelected<Events>) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_events, parent, false)
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]
        holder.itemView.apply {
            Picasso.get().load(event.image)
                .error(R.drawable.events)
                .into(evetns_imageview)
            events_description.text = event.description
            events_title.text = event.title
            events_price.text = Money.toMoney(event.price)
            events_date.text = DateHelper.toDate(event.date)
            events_card_view.setOnClickListener {
                itemSelected.onItemSelected(event)
            }
        }
    }

    class EventsViewHolder(holder: View) : RecyclerView.ViewHolder(holder)
}