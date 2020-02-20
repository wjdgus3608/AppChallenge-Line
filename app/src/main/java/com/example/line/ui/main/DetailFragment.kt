package com.example.line.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.line.R
import com.example.line.databinding.DetailFragmentBinding
import com.example.line.databinding.EditFragmentBinding
import kotlinx.android.synthetic.main.detail_fragment.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.inputBtnMode.observe(this as LifecycleOwner, Observer {
            when(it){
                8->{

                }
                9->{

                }
            }
        })
    }
}