package com.shagalalab.qarejet.ui.transaction.list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction

class TransactionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(transaction: Transaction) {
        itemView.findViewById<ImageView>(R.id.transactionIcon)
            .setBackgroundColor(ContextCompat.getColor(itemView.context, transaction.category.color))
        itemView.findViewById<ImageView>(R.id.transactionIcon).setImageResource(transaction.category.icon)
        itemView.findViewById<TextView>(R.id.transactionCategoryName).text = transaction.category.title
        itemView.findViewById<TextView>(R.id.transactionAccountName).text = transaction.account.title
        itemView.findViewById<TextView>(R.id.transactionAmount).text = transaction.amount.toString()
        itemView.findViewById<TextView>(R.id.transactionDate).text = transaction.date.toString()
    }
}