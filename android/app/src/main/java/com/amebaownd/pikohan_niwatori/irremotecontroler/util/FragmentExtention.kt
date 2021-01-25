package com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main

import android.app.Activity
import androidx.fragment.app.Fragment
import com.amebaownd.pikohan_niwatori.irremotecontroler.util.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = ServiceLoader.provideRepository()
    return ViewModelFactory(repository)
}

fun Activity.getViewModelFactory(): ViewModelFactory {
    val repository = ServiceLoader.provideRepository()
    return ViewModelFactory(repository)
}