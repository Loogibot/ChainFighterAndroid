package com.loogibot.chainfighter.gamestate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.loogibot.chainfighter.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    var fRBinding: FragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fRBinding = FragmentResultBinding.inflate(inflater, container, false)
        return fRBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(finalResult: String) =
            ResultFragment().apply {
                fRBinding.finalResult.text = finalResult
            }
    }
}