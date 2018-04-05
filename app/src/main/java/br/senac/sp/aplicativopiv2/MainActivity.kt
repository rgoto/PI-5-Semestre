package br.senac.sp.aplicativopiv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, LogadoActivity::class.java)
        startActivity(intent)
    }

}
