package com.example.glc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentCurrentGameListBinding

class CurrentGameList : Fragment() {

    private var _binding: FragmentCurrentGameListBinding? = null
    private val binding get() = _binding

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentCurrentGameListBinding.inflate(inflater, container, false)
        val view = binding?.root

        val navController = findNavController()
        navController.navigate(R.id.login_fragment)

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val navController = findNavController()
//        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
//            if (user != null) {
//
//            } else {
//                navController.navigate(R.id.login_fragment)
//            }
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}