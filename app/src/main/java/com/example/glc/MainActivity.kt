package com.example.glc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.glc.databinding.ActivityMainBinding
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.current_game_list,
                R.id.past_game_list,
                R.id.future_game_list
            )
        )

        bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            //Może sealed class
            when (destination.id) {
                R.id.login_fragment -> {
                    hideNavigationBars()
                }

                R.id.add_fragment -> {

                    //UiConfigs.uiConfig[destination.id]

                    //Poszukać dynamic menu updates
                    //https://stackoverflow.com/questions/62026067/navigationcomponent-navigate-away-from-bottomnavitationview/62026782#62026782
                   // hideBottomNavigation()
                }
                R.id.current_game_list -> {
                   // UiConfigs.uiConfig[destination.id]

                }
                //else -> { showBottomNavigation() }
                else -> throw Exception("No config found for ${destination.label}")
            }
        }
    }
    //https:stackoverflow.com/questions/63762233/android-navigation-component-how-to-connect-toolbar-menu-to-the-fragments
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }
//Zrobić -> https://developer.android.com/codelabs/android-navigation?index=..%2F..%2Findex#0
    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            //Sealed class raczej
            R.id.login_fragment -> {
                /*val action = LoginFragmentDirections.actionGlobalLoginFragment(true)
                Firebase.auth.signOut()
                navController.navigate(action)*/
                true
            }
            R.id.add_fragment -> {
                val action = NavGraphDirections.actionGlobalAddFragment()
                navController.navigate(action)
                true
            }
            else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    override fun onBackPressed() = this.finishAfterTransition()

    //Istnieje jakiś dziwny MenuController, chyba niepotrzebny
    private fun hideNavigationBars() = binding.bottomNavView.let {
        it.isVisible = false
        supportActionBar?.hide()
    }

    private fun hideBottomNavigation() = binding.bottomNavView.let {
        it.isVisible = false

    }

    private fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
        supportActionBar?.show()
    }
}
