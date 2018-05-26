package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import br.senac.sp.aplicativopiv2.Utilities.ExternalConnections
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<EditText>(R.id.editLogin)
        login.setText("rafael.fgoto@outlook.com")

        val pass = findViewById<EditText>(R.id.editPass)
        pass.setText("123456")

        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val email = editLogin.text.toString().trim()
        val pass = editPass.text.toString().trim()

        if (email.isEmpty() && pass.isEmpty()) {
            editLogin.error = "E-mail Inválido"
            editPass.error = "Senha Inválida"
            editLogin.hasFocus()
        }
        else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editLogin.error = "E-mail Inválido"
            editLogin.hasFocus()
        }
        else if (pass.isEmpty()) {
            editPass.error = "Senha Inválida"
            editPass.hasFocus()
        }
        else {
            val thread = Thread(Runnable {
                kotlin.run {
                    ExternalConnections.getInstance().loginUser(email, pass, applicationContext)
                }
            })
            thread.start()

            while (thread.isAlive) {
                Toast.makeText(applicationContext, "Wait...", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
