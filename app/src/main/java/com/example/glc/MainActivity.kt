package com.example.glc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.glc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigationView = binding.bottomNavView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        //1.Po zalogowaniu należy przejść przejść tutaj z powrotem i wykorzystać SetUpWithNaviga (below) w tej instrukcji warunkowej
        //2. Pomysł na to by bottomNavigationView było domyslnie ukryte i dopiero po logowaniu było dokryte
    }
}
