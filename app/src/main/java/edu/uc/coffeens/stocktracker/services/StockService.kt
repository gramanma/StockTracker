package edu.uc.coffeens.stocktracker.services

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
    fun fetchStocks(): MutableLiveData<ArrayList<Stock>> {

        var _stocks = MutableLiveData<ArrayList<Stock>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IStockDAO::class.java)
        val call = service?.getAllStocks()
        call?.enqueue(object : Callback<ArrayList<Stock>> {
            override fun onFailure(call: Call<ArrayList<Stock>>, t: Throwable) {
                print("Unable to retrieve service response.")
            }

            override fun onResponse(
                call: Call<ArrayList<Stock>>,
                response: Response<ArrayList<Stock>>
            ) {
                _stocks.value = response.body()
            }

        })

        return _stocks

    }

}