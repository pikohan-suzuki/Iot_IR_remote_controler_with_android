package com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.Event

class MainFragmentViewModel(private val repository:Repository):ViewModel(){

        private var _navigateToTvEvent = MutableLiveData<Event<Boolean>>(Event(false))
        val navigateToTvEvent : LiveData<Event<Boolean>> = _navigateToTvEvent
        fun start(){

        }

        fun onTvButtonClicked(){
                _navigateToTvEvent.value = Event(true)
        }
}