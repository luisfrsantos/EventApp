package com.example.eventsapp.events.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.R
import com.example.eventsapp.events.model.Events
import com.example.eventsapp.events.ui.DetailsEventsActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.main_fragment.*

class EventsFragment : Fragment(), ItemSelected<Events> {

    private val viewModel: EventsViewModel by activityViewModels()
    private lateinit var eventsAdapter: EventsAdapter
    private var events: ArrayList<Events> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onResume() {
        super.onResume()
        eventsAdapter = EventsAdapter(events, this)
        events_recycler_view.layoutManager = LinearLayoutManager(context)
        events_recycler_view.adapter = eventsAdapter
        viewModel.loadEventsData().observe(this, Observer { viewData ->
            when (viewData.state) {
                ViewState.SUCCESS -> {
                    events_progress.visibility = View.GONE
                    viewData.data?.let {
                        events.addAll(it)
                        eventsAdapter.notifyDataSetChanged()
                    }
                }
                ViewState.LOADING -> {
                    events_progress.visibility = View.VISIBLE
                }
                ViewState.ERROR -> {
                    events_progress.visibility = View.GONE
                    events_message.text = viewData.message
                    events_message.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemSelected(events: Events) {
        val intent = Intent(activity, DetailsEventsActivity::class.java).apply {
            putExtra(EXTRA_ID, events.id)
        }
        startActivity(intent)
    }
}