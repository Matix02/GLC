package com.example.glc.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.glc.R
import com.example.glc.databinding.FragmentCurrentGameListBinding
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
//Zmienić nazwę fragmentów na CurrentGameListFragment and so on
const val TAG = "CurrentGameList"

class CurrentGameList : Fragment() {

    private var _binding: FragmentCurrentGameListBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrentGameListBinding.inflate(inflater, container, false)
        Log.d("Creating2", "onCreateView")

        return binding?.root
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
