package com.shagalalab.qarejet.ui.record

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.util.getCurrencySign
import com.shagalalab.qarejet.util.getSign
import com.shagalalab.qarejet.util.isIncome
import com.shagalalab.qarejet.util.toShortDateTime
import java.util.Calendar

class RecordsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(transaction: Transaction) {
        val icon = itemView.findViewById<ImageView>(R.id.transactionIcon)
        icon.background.setColorFilter(ContextCompat.getColor(itemView.context, itemView.resources.getIdentifier(transaction.category.color, "color", itemView.context.packageName)), PorterDuff.Mode.SRC_OVER)
        icon.setImageResource(itemView.resources.getIdentifier(transaction.category.icon, "drawable", itemView.context.packageName))

        itemView.findViewById<TextView>(R.id.transactionCategoryName).text = transaction.category.title
        itemView.findViewById<TextView>(R.id.transactionAccountName).text = transaction.account.title
        itemView.findViewById<TextView>(R.id.transactionAmount).setTextColor(
            ContextCompat.getColor(itemView.context, if (transaction.isIncome()) R.color.green else R.color.red)
        )
        itemView.findViewById<TextView>(R.id.transactionAmount).text = transaction.getSign()
            .plus(transaction.amount)
            .plus(" ")
            .plus(transaction.account.getCurrencySign())

        val calendar = Calendar.getInstance()
        calendar.time = transaction.date
        itemView.findViewById<TextView>(R.id.transactionDate).text = calendar.toShortDateTime()
    }
}