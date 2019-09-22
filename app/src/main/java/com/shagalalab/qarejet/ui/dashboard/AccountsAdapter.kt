package com.shagalalab.qarejet.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.AccountWithAmount

class AccountsAdapter : RecyclerView.Adapter<AccountsViewHolder>() {

    private var accountsList = listOf<AccountWithAmount>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return AccountsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accountsList.size
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        holder.setItem(accountsList[position])
    }

    fun updateData(items: List<AccountWithAmount>) {
        accountsList = items
        notifyDataSetChanged()
    }
}

class AccountsViewHolder(itemVew: View) : RecyclerView.ViewHolder(itemVew) {
    fun setItem(accountWithAmount: AccountWithAmount) {
        itemView.findViewById<TextView>(R.id.accountTitle).text = accountWithAmount.title
        itemView.findViewById<TextView>(R.id.accountAmount).text = "%.2f %s".format(accountWithAmount.amount, accountWithAmount.currency)
    }
}