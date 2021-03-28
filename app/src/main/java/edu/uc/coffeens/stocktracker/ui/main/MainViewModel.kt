package edu.uc.coffeens.stocktracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.services.StockService

class MainViewModel : ViewModel() {
    var stock: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()
    var stockService: StockService = StockService()
    private lateinit var firestore : FirebaseFirestore
    private var storageReferenence = FirebaseStorage.getInstance().getReference()

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
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener {
                Log.d("Firebase", "save failed")
            }
    }
}