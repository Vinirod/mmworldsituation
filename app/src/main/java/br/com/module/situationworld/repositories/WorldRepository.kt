package br.com.module.situationworld.repositories

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import br.com.module.situationworld.apis.Config.createService
import br.com.module.situationworld.apis.WorldApi
import br.com.module.situationworld.models.Historical
import br.com.module.situationworld.models.World

import retrofit2.Call
import retrofit2.Response

class WorldRepository private constructor(){

    private val mWorldAPI: WorldApi

    private var mMLDWorld: MutableLiveData<World>

    private var mMLDHistorical: MutableLiveData<Historical>

    fun getWorld(): MutableLiveData<World>{
        val callHistorical = mWorldAPI.getWorld()
        callHistorical.enqueue(object : retrofit2.Callback<World> {
            override fun onResponse(call: Call<World>, response: Response<World>) {
                if (response.code() == 200) {
                    mMLDWorld.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<World>, t: Throwable) {
                Log.e("Error:", t.message)
            }
        })
        return mMLDWorld
    }

    fun getHistorical(
        mLnContent: LinearLayout,
        mPrgWorld: ProgressBar
    ): MutableLiveData<Historical>{
        val callHistorical = mWorldAPI.getHistorical()
        callHistorical.enqueue(object : retrofit2.Callback<Historical>{
            override fun onResponse(call: Call<Historical>, response: Response<Historical>) {
                if(response.code() == 200){
                    mMLDHistorical.postValue(response.body())
                    mPrgWorld.visibility = View.GONE
                    mLnContent.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<Historical>, t: Throwable) {
                Log.e("Error: ", t.message)
            }
        })
        return mMLDHistorical
    }



    companion object{
        val instance = WorldRepository()
    }

    init{
        mWorldAPI = createService(WorldApi::class.java)
        mMLDWorld = MutableLiveData()
        mMLDHistorical = MutableLiveData()

    }
}