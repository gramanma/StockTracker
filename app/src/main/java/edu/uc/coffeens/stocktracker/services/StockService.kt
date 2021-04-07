package edu.uc.coffeens.stocktracker.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import edu.uc.coffeens.stocktracker.dao.IStockDAO
import edu.uc.coffeens.stocktracker.dto.Stock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockService {

    /**
     * This method grabs JSON data using Retrofit and returns it in an Array
     * @return an array of stocks.
     */
    fun fetchStock(): MutableLiveData<ArrayList<Stock>> {

        var _stock = MutableLiveData<ArrayList<Stock>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IStockDAO::class.java)
        val call = service?.getAllStocks()
        call?.enqueue(object : Callback<ArrayList<Stock>> {
            override fun onFailure(call: Call<ArrayList<Stock>>, t: Throwable) {
                Log.e("Retrofit", "unable to receive service response")
            }

            override fun onResponse(
                call: Call<ArrayList<Stock>>,
                response: Response<ArrayList<Stock>>
            ) {
                _stock.value = response.body()
            }

        })

        return _stock

    }



}