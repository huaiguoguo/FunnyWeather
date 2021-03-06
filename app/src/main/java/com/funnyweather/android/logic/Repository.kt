package com.funnyweather.android.logic

import androidx.lifecycle.liveData
import com.funnyweather.android.logic.model.Place
import com.funnyweather.android.logic.network.FunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        val result = try{
            val placeResponse = FunnyWeatherNetWork.searchPlace(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

}