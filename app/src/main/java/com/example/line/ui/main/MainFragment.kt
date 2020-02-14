package com.example.line.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.line.R
import com.example.line.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel= MainViewModel()
        val binding=
            DataBindingUtil.inflate<MainFragmentBinding>(inflater,R.layout.main_fragment,container,false)
        binding.setVariable(BR.vm, viewModel)
        binding.setLifecycleOwner { lifecycle }
        return binding.root
    }

}
