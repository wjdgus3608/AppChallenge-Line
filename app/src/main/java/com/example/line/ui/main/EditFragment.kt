package com.example.line.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.line.R
import com.example.line.databinding.EditFragmentBinding
import com.example.line.databinding.InputTypeDialogBinding
import com.example.line.databinding.InputUrlDialogBinding


class EditFragment(parentViewModel: MainViewModel) :Fragment(){
    val viewModel by lazy { parentViewModel }
    var typeDialog:AlertDialog?=null
    var urlDialog:AlertDialog?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=
            DataBindingUtil.inflate<EditFragmentBinding>(inflater,
                R.layout.edit_fragment,container,false)
        binding.setVariable(BR.vm, viewModel)
        binding.setLifecycleOwner { lifecycle }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var typeSelectView=
            DataBindingUtil.inflate<InputTypeDialogBinding>(layoutInflater,R.layout.input_type_dialog,view as ViewGroup,false)
        typeSelectView.setVariable(BR.vm,viewModel)
        val builder = AlertDialog.Builder(context!!)
        typeDialog=builder.setView(typeSelectView.root).create()
        var urlInputView=
            DataBindingUtil.inflate<InputUrlDialogBinding>(layoutInflater,R.layout.input_url_dialog,view,false)
        urlInputView.setVariable(BR.vm,viewModel)
        urlDialog=builder.setView(urlInputView.root).create()
        viewModel.inputBtnMode.observe(this as LifecycleOwner, Observer {
            typeDialog?.dismiss()
            urlDialog?.dismiss()
            when(it){
                0-> typeDialog?.show()
                3-> urlDialog?.show()
                4->{
                    viewModel.urlSubmitBtnClick()
                    urlInputView.urlInputView.text?.clear()
                }
                5->{
                    viewModel.urlCancelBtnClick()
                    urlInputView.urlInputView.text?.clear()
                }
            }
        })

    }
}