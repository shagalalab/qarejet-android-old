package com.shagalalab.qarejet.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.ui.category.CategoryFragment
import com.shagalalab.qarejet.ui.chart.ChartsFragment
import com.shagalalab.qarejet.ui.record.RecordsFragment
import com.shagalalab.qarejet.ui.transaction.AddTransactionActivity
import com.shagalalab.qarejet.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.joda.time.DateTime
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as QarejetApp).component.inject(this)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            presenter.openNewTransactionScreen()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        presenter.handleDrawerItemSelection(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private val navigator: Navigator = object : SupportAppNavigator(this, supportFragmentManager, R.id.mainLayout) {
        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
            return when (screenKey) {
                Constants.SCREEN_ADD_TRANSACTION -> Intent(this@MainActivity, AddTransactionActivity::class.java)
                else -> null
            }
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment {
            return when (screenKey) {
                Constants.SCREEN_DASHBOARD -> Fragment()
                Constants.SCREEN_RECORDS -> RecordsFragment()
                Constants.SCREEN_CHARTS -> ChartsFragment()
                Constants.SCREEN_CATEGORY -> CategoryFragment.newInstance(data as Pair<Long, DateTime>)
                Constants.SCREEN_SETTINGS -> Fragment()
                else -> Fragment()
            }
        }
    }
}
