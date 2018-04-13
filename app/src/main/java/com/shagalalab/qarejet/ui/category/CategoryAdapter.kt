package com.shagalalab.qarejet.ui.category

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction

class CategoryAdapter : RecyclerView.Adapter<CategoryItemViewHolder>() {
    private var transactionList: List<Transaction> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_category, parent, false)
        return CategoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder?, position: Int) {
        holder?.setItem(transactionList[position])
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    fun updateData(data: List<Transaction>) {
        transactionList = data
        notifyDataSetChanged()
    }
}

class CategoryItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(transaction: Transaction) {
        itemView.findViewById<TextView>(R.id.itemCategoryDate).text = transaction.date.toString()
        itemView.findViewById<TextView>(R.id.itemCategoryAmount).text = transaction.amount.toString()
    }
}