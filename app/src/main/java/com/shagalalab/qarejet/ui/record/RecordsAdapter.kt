package com.shagalalab.qarejet.ui.record

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction

class RecordsAdapter : RecyclerView.Adapter<RecordsViewHolder>() {
    private var transactionsList = listOf<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_transaction, parent, false)
        return RecordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordsViewHolder?, position: Int) {
        holder?.setItem(transactionsList[position])
    }

    override fun getItemCount()= transactionsList.size

    fun update(transactions: List<Transaction>) {
        transactionsList = transactions
        notifyDataSetChanged()
    }
}