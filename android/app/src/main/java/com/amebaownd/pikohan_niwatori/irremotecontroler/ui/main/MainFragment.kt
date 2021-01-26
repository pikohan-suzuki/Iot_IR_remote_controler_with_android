package com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main

import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.amebaownd.pikohan_niwatori.irremotecontroler.MainActivity

import com.amebaownd.pikohan_niwatori.irremotecontroler.databinding.MainFragmentBinding
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.EventObserver
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.getViewModelFactory

class MainFragment :Fragment(){

    private val viewModel:MainFragmentViewModel by viewModels{getViewModelFactory((this.activity as MainActivity).application)}
    private lateinit var mainFragmentBinding:MainFragmentBinding

    override fun onCreateView(
        inflater:LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View{
        mainFragmentBinding=MainFragmentBinding.inflate(inflater,container,false).apply{
            viewModel=this@MainFragment.viewModel
            lifecycleOwner=viewLifecycleOwner
        }
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view:View,savedInstanceState:Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel.navigateToTvEvent.observe(this.viewLifecycleOwner, EventObserver(){
            if(it){
                navigateToTvFragment()
            }
        })
    }

    private fun navigateToTvFragment(){
        val action = MainFragmentDirections
            .actionMainFragmentToTvFragment()
        findNavController().navigate(action)
    }

}