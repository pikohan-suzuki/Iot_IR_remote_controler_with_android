package com.amebaownd.pikohan_niwatori.irremotecontroler.util

import com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main.Repository

object ServiceLoader {
    var repository: Repository? = null

    fun provideRepository(): Repository {
        synchronized(this) {
            return repository
                ?: createRepository()
        }
    }

    private fun createRepository(): Repository {
        val result = Repository()
        repository = result
        return result
    }
}