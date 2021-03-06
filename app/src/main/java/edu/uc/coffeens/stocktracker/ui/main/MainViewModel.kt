package edu.uc.coffeens.stocktracker.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.dto.WatchlistItem
import edu.uc.coffeens.stocktracker.services.StockService

class MainViewModel : ViewModel() {
    var stock: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()
    var stockService: StockService = StockService()

    private lateinit var firestore: FirebaseFirestore
    private var storageReference = FirebaseStorage.getInstance().getReference()


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

    /**
     * Saves a stock to a users watchlist in Firebase like this:
     * UserLists/UserID/watchlist: [array of stocks]
     *
     * This method creates a collection under the userId of a given
     * user to give them a separate watchlist
     *
     * TODO: Not sure if this implementation is correct.
     *
     * @param stock Stock to be saved
     */
    fun save(stock: Stock) {
        var user = FirebaseAuth.getInstance().currentUser
        val collection = user?.let {
            firestore.collection("UserLists")
                .document(it.uid)
                .collection("watchlist")
                .add(stock)
                .addOnSuccessListener {
                    Log.i(
                        "Firebase",
                        "Successfully added $stock to user ${user.uid} watchlist."
                    )
                }
        }
    }

    fun getWatchList(): List<WatchlistItem> {
        val list = ArrayList<WatchlistItem>()
        var user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            firestore.collection("UserLists")
                .document(user.uid)
                .collection("watchlist").get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(TAG, "${document.data}")
                        val stockTicker = document["stockTicker"]
                        val stockCompany = document["stockCompany"]
                        val stockPrice = document["stockPrice"]
                        val item = WatchlistItem(stockTicker.toString(), stockCompany.toString(),stockPrice.toString())
                        list += item
                    }
                }
        }
        return list
    }

    fun generateTestList(size: Int): List<WatchlistItem> {
        val list = ArrayList<WatchlistItem>()
        for (i in 0 until size) {
            val item = WatchlistItem("Ticker $i", "Company $i", "Stock Price $$i")
            list += item
        }
        return list
    }

    /**
     * Removes a stock to a users watchlist in Firebase like this:
     * UserLists/UserID/watchlist:stock
     *
     * This method removes a stock from a collection under the userId of a given
     * user to allow them to remove certain stocks.
     *
     * TODO: Not sure if this implementation is correct.
     *
     * @param stock Stock to be removed
     */
    fun delete(stock: Stock) {
        var user = FirebaseAuth.getInstance().currentUser
        val collection = user?.uid?.let {
            firestore.collection("UserLists")
                .document(it)
                .collection("watchlist")
                .document(stock.toString())
                .delete()
                .addOnSuccessListener {
                    Log.i(
                        "Firebase",
                        "Successfully removed $stock from user ${user.uid} watchlist."
                    )
                }
        }
    }
}