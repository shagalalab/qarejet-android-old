package com.shagalalab.qarejet.ui.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Category

class CategoryAdapter(context: Context, private val categories: List<Category>) : BaseAdapter() {
    private val filteredCategories = ArrayList<Category>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.spinner_item, parent, false)
        val text = view as TextView
        text.text = getItem(position).title

        return view
    }

    override fun getItem(position: Int): Category {
        return filteredCategories[position]
    }

    override fun getItemId(position: Int): Long {
        return filteredCategories[position].id
    }

    override fun getCount(): Int {
        return filteredCategories.size
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val text = view as TextView
        text.text = getItem(position).title

        return view
    }

    fun filterByType(type: Int) {
        filteredCategories.clear()
        categories.filterTo(filteredCategories) { it.type == type }
        notifyDataSetChanged()
    }
}