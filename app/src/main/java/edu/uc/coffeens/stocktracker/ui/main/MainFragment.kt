package edu.uc.coffeens.stocktracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.uc.coffeens.stocktracker.R
import edu.uc.coffeens.stocktracker.dto.Stock
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    /**
     * Result Codes
     */
    private val RESULT_OK = 200
    private val AUTH_REQUEST_CODE = 9000

    private var user: FirebaseUser? = null

    companion object {
        private var user: FirebaseUser? = null
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    /**
     * This is what to do when the View is rendered.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    /**
     *  This is what happened when the activity is rendered.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.stock.observe(viewLifecycleOwner, Observer { stocks ->
            actStock.setAdapter(
                ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, stocks)
            )
        })


        val watchList = viewModel.getWatchList()
        rvUserList.adapter = WatchlistAdapter(watchList)
        rvUserList.layoutManager = LinearLayoutManager(context)
        rvUserList.setHasFixedSize(true)


        actStock.setOnItemClickListener { parent, view, position, id ->
            var selectedStock = parent.getItemAtPosition(position) as Stock
            tvStockDescription.text = "Company Description:\n\n" + selectedStock.stockDescription + "\n"
            btnSaveToList.visibility = View.VISIBLE

            btnSaveToList.setOnClickListener {

                //Remove all the elements.
                btnSaveToList.visibility = View.GONE
                tvStockDescription.text = null
                actStock.text = null

                //Save the stock to a list
                viewModel.save(selectedStock)
                Toast.makeText(
                    context,
                    "${selectedStock.stockTicker} saved to watchlist",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        viewModel.fetchStock()

        user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            btnLogin.text = "Logout";
        } else if (user == null) {
            btnLogin.text = "Login"
        }
        btnLogin.setOnClickListener {
            logUserInOrOut()
        }
    }

    /**
     * Function to be called by the log on button
     */
    private fun logUserInOrOut() {
        user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            var providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                    .build(), AUTH_REQUEST_CODE
            )

        } else if (user != null) {
            Log.i("Firebase", "user " + user + "signed out successfully.")
            FirebaseAuth.getInstance().signOut()
        }
    }

    /**
     * What to do when a result code is returned.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == AUTH_REQUEST_CODE) {
                user = FirebaseAuth.getInstance().currentUser
                Log.i("Firebase", "user " + user + "signed in successfully.")
            }
        }
    }

}