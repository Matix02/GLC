package com.example.glc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glc.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        //Czy RV w onCreateView czy onCreatedView
        val recyclerView = binding?.rvAdd

        recyclerView?.setHasFixedSize(true)

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = GamesAdapter(getSampleGames())

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
/* !TO DO!
* - Sprawić, by niezależnie z jakiego Fragmentu włączy się opcję "+", to skończywszy działanie w nowootwartym fragmencie wrócić w miejsce gdzie się było z Strzałką <-
* - Na belce lub pod nią dodać opcję SearchView
* - Zaimplementować metode wyszukiwania/filtracji na tych podstawowych danych z dwóch gier
* - Dołączyć działanie kontrolera, który schowa i przywróci BottomNavigationView, kiedy użytkownik będzie w "Add" Fragment
* - Przekonwertować Adapter do użytku ViewBinding
* - Poszukać rozwiązania na MemoryLeak - Zza.zza
*  */

/* !Info!
* 1. Animation Content Size Change - https://developer.android.com/codelabs/jetpack-compose-animation?hl=en&continue=https%3A%2F%2Fcodelabs.developers.google.com%2F%3Fcat%3Dandroid#4
* by rozszerzać grafiki reprezentujące gry i wyświetlić ?guziki?
* 2. Animating a Simple Value Change - https://developer.android.com/codelabs/jetpack-compose-animation?hl=en&continue=https%3A%2F%2Fcodelabs.developers.google.com%2F%3Fcat%3Dandroid#2
* by zmienić kolor obramowania karty po przejśiu na inną
* 3. Repeated Animation - https://developer.android.com/codelabs/jetpack-compose-animation?hl=en&continue=https%3A%2F%2Fcodelabs.developers.google.com%2F%3Fcat%3Dandroid#6
* by zrobić szary indicator, do ładowania danych
* 4.
*
 */