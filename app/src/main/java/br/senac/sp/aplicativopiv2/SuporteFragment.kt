package br.senac.sp.aplicativopiv2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import br.senac.sp.aplicativopiv2.Utilities.UserData

class SuporteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_suporte, container, false)

        val email = view.findViewById<EditText>(R.id.userEmail)
        email.setText(UserData.getInstance().email)

        val buttonSend = view.findViewById<View>(R.id.buttonSend)

        buttonSend.setOnClickListener {
            sendEmail(view)
        }

        return view
    }

  /*  companion object {
        fun newInstance(): SuporteFragment {
            return SuporteFragment()
        }
    }*/

    private fun sendEmail(view: View) {
        val subject = view.findViewById<EditText>(R.id.editSubject)
        val message = view.findViewById<EditText>(R.id.message)
        val email = view.findViewById<EditText>(R.id.userEmail)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, email.text.toString().trim())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.text.toString().trim())
        intent.putExtra(Intent.EXTRA_TEXT, message.text.toString().trim())

        try {
            startActivity(Intent.createChooser(intent, "Escolha um cliente de e-mail"))
            this.fragmentManager.beginTransaction().replace(R.id.flContent, ChartLineFragment()).commit()
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
