package com.shagalalab.qarejet.ui.chart

import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.CategoryWithAmount

class ChartsDistributionAdapter(private val listener: ChartsFragment.Listener) : RecyclerView.Adapter<ChartsDistributionViewHolder>() {
    private var categoriesList = listOf<CategoryWithAmount>()
    private var totalAmount = 0.0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChartsDistributionViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_chart, parent, false)
        return ChartsDistributionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChartsDistributionViewHolder?, position: Int) {
        holder?.setItem(categoriesList[position], totalAmount, listener)
    }

    override fun getItemCount() = categoriesList.size

    fun updateData(items: List<CategoryWithAmount>) {
        totalAmount = items.sumByDouble { it.amount }
        categoriesList = items
        notifyDataSetChanged()
    }
}

class ChartsDistributionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(categoryWithAmount: CategoryWithAmount, totalAmount: Double, listener: ChartsFragment.Listener) {
        itemView.setOnClickListener { listener.onChartItemClicked(categoryWithAmount.category.id) }

        val ratio = categoryWithAmount.amount * 100 / totalAmount
        itemView.findViewById<TextView>(R.id.itemChartPercent).text = "%.0f".format(ratio).plus("%")
        (itemView.findViewById<TextView>(R.id.itemChartPercent).background as GradientDrawable)
            .setColor(ContextCompat.getColor(itemView.context, categoryWithAmount.category.color))
        itemView.findViewById<TextView>(R.id.itemChartCategory).text = categoryWithAmount.category.title
        itemView.findViewById<TextView>(R.id.itemChartAmount).text = "%.2f".format(categoryWithAmount.amount)
    }
}