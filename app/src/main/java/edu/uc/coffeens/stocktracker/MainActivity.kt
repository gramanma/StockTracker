package edu.uc.coffeens.stocktracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uc.coffeens.stocktracker.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    /**
     * This is what happens when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}