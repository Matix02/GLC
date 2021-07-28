package com.example.glc

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentCurrentGameListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentGameList : Fragment() {

    private var _binding: FragmentCurrentGameListBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrentGameListBinding.inflate(inflater, container, false)
        val view = binding?.root

        Log.d("Creating2", "onCreateView")


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Creating2", "onViewCreated")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
//3. Przetesotwać tą instrukcję z codelabs 6.1 i zobaczyc jak działa
