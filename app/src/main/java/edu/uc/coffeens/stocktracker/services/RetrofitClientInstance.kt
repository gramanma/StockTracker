package edu.uc.coffeens.stocktracker.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is a run-of-the-mill RCI, helps fetch data from from a JSON feed and converts it to some usable data.
 */
object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://gist.githubusercontent.com/"

    val retrofitInstance: Retrofit?
        get() {
            //Has this object been created?
            if (retrofit == null) {
                // Create retrofit instance
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

}