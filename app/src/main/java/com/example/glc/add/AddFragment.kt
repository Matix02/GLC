package com.example.glc.add

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glc.MyApplication
import com.example.glc.R
import com.example.glc.add.adapter.GamesAdapter
import com.example.glc.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_add.*
import javax.inject.Inject

private const val TAG = "AddRepositoryAction"

class AddFragment : Fragment() {

    @Inject
    lateinit var addViewModel: AddViewModel

    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var binding: FragmentAddBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        showSearchedGames()
    }

    private fun showSearchedGames(title: String = "name;search \"Halo\";") {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            with(addViewModel) {
                searchGame(title)
                addGameList.observe(viewLifecycleOwner, { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            shouldShowProgressBar(false)
                            response.data?.let { gamesAdapter.games = it }
                            checkListCount()
                        }
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                        }
                        is NetworkResult.Loading -> { shouldShowProgressBar(true) }
                    }
                })
            }
        }
    }

    private fun setupRecycleView() {
        binding.addRecycleView.apply {
            gamesAdapter = GamesAdapter()
            adapter = gamesAdapter
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun checkListCount() {
        val isListEmpty = gamesAdapter.itemCount == 0
        emptyList.isVisible = isListEmpty
    }

    private fun shouldShowProgressBar(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }
}
//!!!!Dokończyć z tego filmiku, który jest w postaci dużej listy.

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