package com.example.eventsapp.events.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.eventsapp.R
import com.example.eventsapp.commons.helper.DateHelper
import com.example.eventsapp.commons.helper.Money
import com.example.eventsapp.events.model.People
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details_events.*
import kotlinx.android.synthetic.main.adapter_events.view.*
import kotlinx.android.synthetic.main.adapter_people.view.*

@AndroidEntryPoint
class DetailsEventsActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModels()
    lateinit var peopleAdapter: PeopleAdapter
    lateinit var id: String
    private val peoples: ArrayList<People> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_events)
        setSupportActionBar(event_toolbar)

        peopleAdapter = PeopleAdapter(peoples)
        event_people_recycler_view.adapter = peopleAdapter

        id = intent.getStringExtra(EXTRA_ID)!!
        viewModel.loadEventData(id)
            .observe(this, Observer { viewData ->
                when (viewData.state) {
                    ViewState.SUCCESS -> {
                        viewData.data?.apply {
                            Picasso.get().load(image)
                                .error(R.drawable.events)
                                .into(event_image_view)

                            event_title.text = title
                            event_description.text = description
                            event_price.text = Money.toMoney(price)
                            event_date.text = DateHelper.toDate(date)
                            peoples.addAll(people)
                            peopleAdapter.notifyDataSetChanged()
                        }
                    }
                    ViewState.LOADING -> {}
                    ViewState.ERROR -> {}
                }
            })

        event_checkin_button.setOnClickListener {
            CheckintDialogFragment(id).show(
                supportFragmentManager,
                TAG_FRAGMENT
            )
        }
    }

    companion object {
        const val EXTRA_ID = "id"
        const val TAG_FRAGMENT = "checkintDialogFragment"
    }
}