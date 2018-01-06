package com.shagalalab.qarejet.ui.transaction.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction

class TransactionListAdapter : RecyclerView.Adapter<TransactionViewHolder>() {
    private var transactionsList = listOf<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder?, position: Int) {
        holder?.setItem(transactionsList[position])
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    fun update(transactions: List<Transaction>) {
        transactionsList = transactions
        notifyDataSetChanged()
    }
}