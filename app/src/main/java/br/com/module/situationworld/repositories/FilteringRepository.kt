package br.com.module.situationworld.repositories

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.module.situationworld.apis.Config
import br.com.module.situationworld.apis.FilteringApi
import br.com.module.situationworld.models.Country

import retrofit2.Call
import retrofit2.Response

class FilteringRepository private constructor(){

    private var mMLDCoutries: MutableLiveData<MutableList<Country>>

    private var mFilteringApi: FilteringApi

    fun getCountries(
        mPrgWorld: ProgressBar,
        mRvCountries: RecyclerView
    ): MutableLiveData<MutableList<Country>> {
        val callCountries = mFilteringApi.getCountries()
        callCountries.enqueue(object : retrofit2.Callback<MutableList<Country>>{

            override fun onResponse(call: Call<MutableList<Country>>, response: Response<MutableList<Country>>) {
                if(response.code() == 200){
                    mMLDCoutries.postValue(response.body())
                    mPrgWorld.visibility = View.GONE
                    mRvCountries.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<MutableList<Country>>, t: Throwable) {
                Log.e("Error: ", t.message)
            }
        })
        return  mMLDCoutries
    }

    init {
        mMLDCoutries = MutableLiveData()
        mFilteringApi = Config.createService(FilteringApi::class.java)
    }

    companion object{
        val instance = FilteringRepository()
    }
}