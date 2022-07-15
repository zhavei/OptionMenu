package com.syafei.optionmenu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syafei.optionmenu.R

class QuoteListAdapter(private val listReview: ArrayList<String>) :
    RecyclerView.Adapter<QuoteListAdapter.QuoteVieWHolder>() {

    class QuoteVieWHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val tvQuote: TextView = view.findViewById(R.id.tv_item_quote_list)
        val tvQuote: TextView = view.findViewById(R.id.tv_Quote_list2)
        val tvauthor: TextView = view.findViewById(R.id.tv_aouthor_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteVieWHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_quote2, parent, false)
        return QuoteVieWHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteVieWHolder, position: Int) {
        holder.tvQuote.text = listReview[position]
        holder.tvauthor.text = listReview[position]
    }

    override fun getItemCount(): Int {
        return listReview.size
    }


}