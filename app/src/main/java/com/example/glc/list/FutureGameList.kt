package com.example.glc.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.glc.databinding.FragmentFutureGameListBinding


class FutureGameList : Fragment() {

    private var _binding: FragmentFutureGameListBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFutureGameListBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}