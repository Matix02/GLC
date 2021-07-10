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
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentCurrentGameListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentGameList : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentCurrentGameListBinding? = null
    private val binding get() = _binding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrentGameListBinding.inflate(inflater, container, false)
        val view = binding?.root
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
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
//1. Zastanowić się na ideą Auth itd., czy to ma być observable itd. i co się dzieje jak jest null i WRACA, że just nie jest
//2. Przetestoweać nadawanie Auth itd. null i poszuakć w necie, czy tak uniknie się leak'ów
//3. Przetesotwać tą instrukcję z codelabs 6.1 i zobaczyc jak działa
