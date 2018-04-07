package br.senac.sp.aplicativopiv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_logado.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.R.attr.fragment
import android.R.attr.fragment





class LogadoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logado)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_suporte -> {
                val fragmentManager = supportFragmentManager
                val fragment: Fragment = RemoveFragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }
            R.id.nav_info -> {
                val fragmentManager = supportFragmentManager
                val fragment: Fragment = SuporteFragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
