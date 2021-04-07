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

}