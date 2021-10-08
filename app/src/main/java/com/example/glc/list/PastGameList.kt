package com.example.glc.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.glc.R

class PastGameList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_past_game_list, container, false)
    }
}
//Co z tym onCreateView vs onViewCreated ? jak ukryć pasek
