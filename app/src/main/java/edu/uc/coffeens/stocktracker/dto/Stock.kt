package edu.uc.coffeens.stocktracker.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO class for a Stock.
 */
data class Stock(
    @SerializedName("symbol") var stockTicker: String,
    @SerializedName("company") var stockCompany: String,
    @SerializedName("description") var stockDescription: String,
    @SerializedName("initial_price") var stockPrice: String
) {

    //Serialization to map the fields from the JSON response to the vars I've put in place


    /**
     * Convert JSON to string for displaying on the search bar
     * @return information about a stock.
     */
    override fun toString(): String {
        return "$stockCompany: $stockTicker \$$stockPrice"
    }

    /**
     * Send a stock to Firebase, where it will be stored in a users list
     * @param symbol ticker to be saved to list
     */
    fun saveToFirebase(symbol: String) {
        //TODO: Add method for saving things to Firebase
        //get user list
        //if stock exists already tell the user
        //if it does not exist add it to list
    }

    /**
     * Remove a stock from a users list, if it exists
     * @param symbol ticker to be removed
     */
    fun removeFromFirebase(symbol: String) {
        //TODO: Add method for removing things from Firebase
        //Grab user list
        //See if string exists
        //if so, remove
        //if not, error
    }

}