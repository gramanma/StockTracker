package edu.uc.coffeens.stocktracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.services.StockService

class MainViewModel : ViewModel() {
    var stocks: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()
    var stockService: StockService = StockService()

    init {
        fetchStocks()
    }

    fun fetchStocks() {
        stocks = stockService.fetchStocks()
    }
}