package com.amebaownd.pikohan_niwatori.irremotecontroler.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amebaownd.pikohan_niwatori.irremotecontroler.MainViewModel
import com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main.MainFragmentViewModel
import com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main.Repository
import com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main.TvFragmentViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val application: Application, private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val t = with(modelClass) {
            when {
                isAssignableFrom(MainFragmentViewModel::class.java) ->
                MainFragmentViewModel(repository!!)
                isAssignableFrom(TvFragmentViewModel::class.java) ->
                    TvFragmentViewModel(application,repository)
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModelClass $modelClass")
            }
        } as T
        return t
    }
}