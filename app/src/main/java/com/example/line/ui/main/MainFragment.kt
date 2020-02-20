package com.example.line.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.line.R
import com.example.line.databinding.MainFragmentBinding

class MainFragment(parentViewModel: MainViewModel) : Fragment() {

    private val viewModel by lazy { parentViewModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=
            DataBindingUtil.inflate<MainFragmentBinding>(inflater,R.layout.main_fragment,container,false)
        binding.setVariable(BR.vm, viewModel)
        binding.setLifecycleOwner { lifecycle }
        return binding.root
    }
}
