package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class SuporteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_suporte, container, false)

        var buttonSend = view.findViewById<View>(R.id.buttonSend)

        buttonSend.setOnClickListener {
            Toast.makeText(context, "Obrigado por entrar em contato conosco!", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object {
        fun newInstance(): SuporteFragment {
            return SuporteFragment()
        }

    }
}
