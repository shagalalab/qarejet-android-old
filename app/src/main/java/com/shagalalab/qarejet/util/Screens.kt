package com.shagalalab.qarejet.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.ui.category.CategoryActivity
import com.shagalalab.qarejet.ui.chart.ChartsFragment
import com.shagalalab.qarejet.ui.dashboard.DashboardFragment
import com.shagalalab.qarejet.ui.record.RecordsFragment
import com.shagalalab.qarejet.ui.transaction.AddTransactionActivity
import org.joda.time.DateTime
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    val SCREEN_ADD_TRANSACTION = object : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            return Intent(context, AddTransactionActivity::class.java)
        }
    }

    val SCREEN_DASHBOARD = object : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return DashboardFragment()
        }
    }

    val SCREEN_RECORDS = object : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RecordsFragment()
        }
    }

    val SCREEN_CHARTS = object : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ChartsFragment()
        }
    }

    val SCREEN_SETTINGS = object : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return Fragment()
        }
    }

    val SCREEN_EMPTY = object : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return Fragment()
        }
    }

    fun getCategoryScreen(data: Pair<Category, DateTime>): SupportAppScreen {
        return object : SupportAppScreen() {
            override fun getActivityIntent(context: Context?): Intent {
                val intent = Intent(context, CategoryActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("data", data)
                intent.putExtras(bundle)
                return intent
            }
        }
    }
}