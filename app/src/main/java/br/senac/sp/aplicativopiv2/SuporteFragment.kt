package br.senac.sp.aplicativopiv2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SuporteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_suporte, container, false)
    }

    companion object {
        fun newInstance(): SuporteFragment {
            return SuporteFragment()
        }
    }
}// Required empty public constructor
