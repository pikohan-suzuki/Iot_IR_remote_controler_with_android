package com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amebaownd.pikohan_niwatori.irremotecontroler.MainActivity
import com.amebaownd.pikohan_niwatori.irremotecontroler.databinding.TvFragmentBinding
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.getViewModelFactory

class TvFragment : Fragment(){

    private val viewModel:TvFragmentViewModel by viewModels{getViewModelFactory((this.activity as MainActivity).application)}
    private lateinit var mainFragmentBinding: TvFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding= TvFragmentBinding.inflate(inflater,container,false).apply{
            viewModel=this@TvFragment.viewModel
            lifecycleOwner=viewLifecycleOwner
        }
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel.start()
    }

//    private fun navigateToAaaa(){
//        val action = MainFragmentDirections
//            .actionMainFragmentToRecordFragment()
//        findNavController().navigate(action)
//    }

}