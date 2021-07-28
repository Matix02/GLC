package com.example.glc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.glc.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), MenuController {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
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
//Już nie ma back Buttona, należy zobaczyć ten Search
        bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.login_fragment) {
                setBotNavToLocked()

            } else {
                setBotNavToUnlocked()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.login_fragment -> {
                val action = LoginFragmentDirections.actionGlobalLoginFragment(true)
                Firebase.auth.signOut()
                navController.navigate(action)
                true
            }
            R.id.add_fragment -> {
                val action = NavGraphDirections.actionGlobalAddFragment()
                navController.navigate(action)
                true
            }  else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        this.finishAfterTransition()
    }

    override fun setBotNavToLocked() {
        binding.bottomNavView.visibility = View.GONE
        supportActionBar?.hide()
    }

    override fun setBotNavToUnlocked() {
        binding.bottomNavView.visibility = View.VISIBLE
        supportActionBar?.show()
    }
}
