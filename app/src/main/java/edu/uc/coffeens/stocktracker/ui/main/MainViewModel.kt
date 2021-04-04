package edu.uc.coffeens.stocktracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.services.StockService

class MainViewModel : ViewModel() {
    var stock: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()
    var stockService: StockService = StockService()
    private var firestore : FirebaseFirestore

    /**
     * Initialize the MVM.
     */
    init {
        fetchStock()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    /**
     * Gets the stocks using the stock service.
     */
    fun fetchStock() {
        stock = stockService.fetchStock()
    }

    fun save(stocks: Stock) {
        firestore.collection("Stocks")
            .document()
            .set(stocks)
            .addOnSuccessListener {
                Log.e("Firebase", "document saved")
            }
            .addOnFailureListener {
                Log.e("Firebase", "save failed")
            }
    }
}