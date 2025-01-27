package com.example.line.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.example.line.R
import com.example.line.databinding.DetailFragmentBinding

class DetailFragment(parentViewModel: MainViewModel) :Fragment(){
    val viewModel by lazy { parentViewModel }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=
            DataBindingUtil.inflate<DetailFragmentBinding>(inflater,
                R.layout.detail_fragment,container,false)
        binding.setVariable(BR.vm, viewModel)
        binding.setLifecycleOwner { lifecycle }
        return binding.root
    }
}