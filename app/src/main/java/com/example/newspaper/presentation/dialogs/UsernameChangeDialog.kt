package com.example.newspaper.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.newspaper.R
import com.example.newspaper.databinding.DialogUsernameChangeBinding
import javax.inject.Inject

class UsernameChangeDialog: DialogFragment() {

    @Inject
    lateinit var viewModelFactory: UsernameChangeDialogModelFactory

    private val viewModel: UsernameChangeDialogViewModel by viewModels { viewModelFactory }

    private var _binding: DialogUsernameChangeBinding? = null
    private val binding: DialogUsernameChangeBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.DefaultDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogUsernameChangeBinding.inflate(layoutInflater)

        binding.dialogUsernameChangeOkButton.setOnClickListener {
            if(binding.usernameChangeEditText.length() != 0) {
                viewModel.setUsername(binding.usernameChangeEditText.text.toString())
            }
        }

        return binding.root
    }


}