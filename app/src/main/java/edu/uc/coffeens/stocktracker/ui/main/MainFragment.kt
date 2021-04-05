package edu.uc.coffeens.stocktracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.uc.coffeens.stocktracker.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    /**
     * Result Codes
     */
    private val RESULT_OK = 200
    private val AUTH_REQUEST_CODE = 9000


    private var user: FirebaseUser? = null

    companion object {
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
                ArrayAdapter(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    stocks
                )
            )
        })
        viewModel.fetchStock()
        btnLogin.setOnClickListener {
            logon()
        }
    }

    fun getClickedStock(view: View) {
        Log.d("[SEARCH BAR]", "Clicked a stock.")
    }
    
    /**
     * Function to be called by the log on button
     */
    private fun logon() {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build(), AUTH_REQUEST_CODE
        )
        //TODO: Add logic for detecting if user is signed in.
    }

    /**
     * What to do when a result code is returned.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == AUTH_REQUEST_CODE) {
                user = FirebaseAuth.getInstance().currentUser
            }
        }
    }

}
