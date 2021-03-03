package edu.uc.coffeens.stocktracker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import edu.uc.coffeens.stocktracker.dto.Stock
import edu.uc.coffeens.stocktracker.ui.main.MainViewModel
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Test the stock logic.
 * Validate that the DTO works as expected.
 * Validate the format of the DTO string.
 * Validate that the fetch contains a minimum number of records.
 * Validate that "3M" is one of the stocks returned.
 */

class StockUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm: MainViewModel

    @Before
    fun populateStocks() {
        mvm = MainViewModel()

    }

    @Test
    fun stockDTO_maintainsState() {
        var stock = Stock(
            "MMM",
            "3M",
            "3M, based in Minnesota, may be best known for its Scotch tape and Post-It Notes, but it also produces sand paper, adhesives, medical products, computer screen filters, food safety items, stationery products and many products used in automotive, marine, and aircraft industries.",
            "44.28")
        assertTrue(stock.stockTicker.equals("MMM"))
        assertTrue(stock.stockCompany.equals("3M"))
    }

    @Test
    fun stockDTO_toStringFormat() {
       var stock = Stock(
            "MMM",
            "3M",
            "3M, based in Minnesota, may be best known for its Scotch tape and Post-It Notes, but it also produces sand paper, adhesives, medical products, computer screen filters, food safety items, stationery products and many products used in automotive, marine, and aircraft industries.",
            "44.28")
        assertTrue(stock.toString().equals("3M: MMM \$44.28"))
    }

    @Test
    fun stockDTO_isPopulated() {
        givenViewModelIsInitialized()
        whenJSONDataAreReadAndParsed()
        thenTheCollectionSizeShouldBeGreaterThanZero()
    }

    private fun givenViewModelIsInitialized() {

    }

    private fun whenJSONDataAreReadAndParsed() {
        mvm.fetchStocks()
    }

    private fun thenTheCollectionSizeShouldBeGreaterThanZero() {
        var allStocks = ArrayList<Stock>()
        mvm.stocks.observeForever{
            allStocks = it
        }
        Thread.sleep(5000)
        assertNotNull(allStocks)
        assertTrue(allStocks.size > 0)
    }


    @Test
    fun stockDTO_contains3M() {
        givenViewModelIsInitialized()
        whenJSONDataAreReadAndParsed()
        thenResultsShouldContain3M()
    }

    private fun thenResultsShouldContain3M() {
        var contains3M:Boolean = false
        mvm.stocks.observeForever {
            it.forEach {
                if (it.stockCompany.equals("3M")) {
                    contains3M = true
                }
            }
            assertTrue(contains3M)
        }
    }
}
