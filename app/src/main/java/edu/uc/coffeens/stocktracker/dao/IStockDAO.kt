package edu.uc.coffeens.stocktracker.dao

import edu.uc.coffeens.stocktracker.dto.Stock
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IStockDAO {

    @GET("/stevekinney/f96d5800852e91282f46/raw/ea047c2f5898de6c9fecf535db8b30abcfe5b423/stocks.json")

    fun getAllStocks(): Call<ArrayList<Stock>>

    @GET("/stevekinney/f96d5800852e91282f46/raw/ea047c2f5898de6c9fecf535db8b30abcfe5b423/stocks.json")

    fun getStock(@Query("Combined_Name") name: String) : Call<ArrayList<Stock>>

}