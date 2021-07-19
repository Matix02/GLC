package com.example.glc

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
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

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.login_fragment) {
                setBotNavToLocked()

            } else {
                setBotNavToUnlocked()
            }
        }


        //1.Po zalogowaniu należy przejść przejść tutaj z powrotem i wykorzystać SetUpWithNaviga (below) w tej instrukcji warunkowej
        //2. Pomysł na to by bottomNavigationView było domyslnie ukryte i dopiero po logowaniu było dokryte
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.login_fragment) {
            val action = LoginFragmentDirections.actionGlobalLoginFragment(true)

            Firebase.auth.signOut()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
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
