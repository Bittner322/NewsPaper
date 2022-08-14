package com.example.newspaper.presentation.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.newspaper.R

class UsernameChangeDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setView(R.layout.dialog_username_change)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}