package br.com.module.situationworld.viewmodels

import android.app.Application
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.module.situationworld.models.Country
import br.com.module.situationworld.repositories.FilteringRepository


class FilteringViewModel(application: Application) : AndroidViewModel(application) {

    private val mFilteringRepository: FilteringRepository

    private var mApplication: Application

    fun getFiltering(
        mPrgWorld: ProgressBar,
        mRvCountries: RecyclerView
    ): MutableLiveData<MutableList<Country>>{
        return mFilteringRepository.getCountries(mPrgWorld, mRvCountries)
    }

    init {
        mFilteringRepository = FilteringRepository.instance
        mApplication = application
    }
}