package br.com.module.situationworld.viewmodels

import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.module.situationworld.models.Historical
import br.com.module.situationworld.models.World
import br.com.module.situationworld.repositories.WorldRepository

class WorldViewModel() : ViewModel() {

    private val mWorldRepository: WorldRepository

    fun getWorld(): MutableLiveData<World> {
        return mWorldRepository.getWorld()
    }

    fun getHistorical(
        mLnContent: LinearLayout,
        mPrgWorld: ProgressBar
    ): MutableLiveData<Historical>{
        return mWorldRepository.getHistorical(mLnContent, mPrgWorld)
    }

    init {
        mWorldRepository = WorldRepository.instance
    }
}