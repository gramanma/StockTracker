package edu.uc.coffeens.stocktracker.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uc.coffeens.stocktracker.R
import edu.uc.coffeens.stocktracker.dto.WatchlistItem

class WatchlistAdapter(private val watchList: List<WatchlistItem>) :
    RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.watchlist_row_layout, parent, false)

        return WatchlistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        val currentItem = watchList[position]

        holder.lblStockTicker.text = currentItem.stockTicker
        holder.lblStockCompany.text = currentItem.stockCompany
        holder.lblStockPrice.text = currentItem.stockPrice

    }

    override fun getItemCount() = watchList.size

    class WatchlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblStockTicker: TextView = itemView.findViewById(R.id.lblStockTicker)
        val lblStockCompany: TextView = itemView.findViewById(R.id.lblStockCompany)
        val lblStockPrice: TextView = itemView.findViewById(R.id.lblStockPrice)

    }
}