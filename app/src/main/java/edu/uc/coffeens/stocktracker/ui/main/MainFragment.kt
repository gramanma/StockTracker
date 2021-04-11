package edu.uc.coffeens.stocktracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
<<<<<<< Updated upstream
=======
import android.widget.Toast
>>>>>>> Stashed changes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        fun getUserID(): String {
            user = FirebaseAuth.getInstance().currentUser
            return user!!.uid
        }
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

        actStock.setOnItemClickListener { parent, view, position, id ->
            var selectedStock = parent.getItemAtPosition(position) as Stock
            print(selectedStock.stockTicker)
            Toast.makeText(context,selectedStock.stockTicker,Toast.LENGTH_LONG).show()
            tvStockDescription.text = selectedStock.stockDescription
        }

        viewModel.fetchStock()
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
            btnLogin.text = "Logout";
        } else if (user != null) {
            Log.i("Firebase", "user " + user + "signed out successfully.")
            FirebaseAuth.getInstance().signOut()
            btnLogin.text = "Login";
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
<<<<<<< Updated upstream
}
=======





}
>>>>>>> Stashed changes
