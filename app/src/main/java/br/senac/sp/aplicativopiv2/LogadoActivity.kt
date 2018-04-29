package br.senac.sp.aplicativopiv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.senac.sp.aplicativopiv2.Utilities.UserData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_logado.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class LogadoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logado)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //Chamando instancia de user populada para o preenchimento do header do navView
        val user = UserData.getInstance()

        val header = nav_view.getHeaderView(0)
        header.textNavEmail.text = user.email.toString().trim()
        header.textNavUser.text = user.name.toString().trim()
        Glide.with(applicationContext).load("http://i.imgur.com/DvpvklR.png")
                .apply(RequestOptions.circleCropTransform()).into(header.imageView)

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
