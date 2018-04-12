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

        //Chamando o view do fragment e substituindo o fragment blank
        supportFragmentManager.beginTransaction().replace(R.id.flContent, ChartLineFragment()).commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_suporte -> {
                supportFragmentManager.beginTransaction().replace(R.id.flContent, SuporteFragment()).commit()
            }

            R.id.nav_analytics -> {
                supportFragmentManager.beginTransaction().replace(R.id.flContent, ChartLineFragment()).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
