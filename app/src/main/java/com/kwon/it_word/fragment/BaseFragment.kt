package com.kwon.it_word.fragment

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    companion object {
        fun newInstance() {

        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_base, container, false)
//    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}