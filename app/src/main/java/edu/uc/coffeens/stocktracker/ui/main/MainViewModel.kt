package edu.uc.coffeens.stocktracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.dto.WatchlistItem
import edu.uc.coffeens.stocktracker.services.StockService

class MainViewModel : ViewModel() {
    var stock: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()
    var stockService: StockService = StockService()
    private val userId = MainFragment.getUserID()
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
        val collection = firestore.collection("UserLists")
            .document(userId)
            .collection("watchlist")
            .add(stock)
            .addOnSuccessListener {
                Log.i(
                    "Firebase",
                    "Successfully added $stock to user $userId watchlist."
                )
            }
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
        val collection = firestore.collection("UserLists")
            .document(userId)
            .collection("watchlist")
            .document(stock.toString())
            .delete()
            .addOnSuccessListener {
                Log.i(
                    "Firebase",
                    "Successfully removed $stock from user $userId watchlist."
                )
            }
    }
}