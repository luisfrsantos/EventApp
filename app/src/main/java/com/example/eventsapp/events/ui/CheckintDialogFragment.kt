package com.example.eventsapp.events.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.eventsapp.R
import com.example.eventsapp.events.model.Checkin
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_checkint_dialog_list_dialog.*

class CheckintDialogFragment(val id: String) : BottomSheetDialogFragment() {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkint_dialog_list_dialog, container, false)
    }

    override fun onResume() {
        super.onResume()
        checkin_image_view_close.setOnClickListener {
            dismiss()
        }
        checkin_button.setOnClickListener {
            checkin_button.isEnabled = false
            when {
                checkin_name.editText?.text.isNullOrBlank() -> {
                    checkin_name.error = "Field is required"
                }
                checkin_email.editText?.text.isNullOrBlank() -> {
                    checkin_name.error = "Field is required"
                }
                else -> {
                    checkin_progress.visibility = View.VISIBLE
                    val checkin = Checkin(
                        id,
                        checkin_name.editText?.text.toString(),
                        checkin_email.editText?.text.toString()
                    )
                    viewModel.checkin(checkin).observe(this, Observer { viewData ->
                        when (viewData.state) {
                            ViewState.SUCCESS -> {
                                checkin_progress.visibility = View.GONE
                                dismiss()
                                Snackbar.make(
                                    requireActivity().findViewById(R.id.checkin_container),
                                    "Send Data Sucess",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                            ViewState.ERROR -> {
                                dismiss()
                                Snackbar.make(
                                    requireActivity().findViewById(R.id.checkin_container),
                                    "No Send Data Sucess",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    })
                }
            }
        }
    }
}