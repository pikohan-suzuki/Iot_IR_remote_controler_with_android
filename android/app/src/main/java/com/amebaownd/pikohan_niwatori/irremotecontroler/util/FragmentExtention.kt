package com.amebaownd.pikohan_niwatori.irremotecontroler.util

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.ViewModelFactory

fun Fragment.getViewModelFactory(application:Application): ViewModelFactory {
    val repository = ServiceLoader.provideRepository()
    return ViewModelFactory(application,repository)
}

fun Activity.getViewModelFactory(application:Application): ViewModelFactory {
    val repository = ServiceLoader.provideRepository()
    return ViewModelFactory(application,repository)
}