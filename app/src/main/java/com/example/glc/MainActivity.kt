package com.example.glc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController


        //1.Po zalogowaniu należy przejść przejść tutaj z powrotem i wykorzystać SetUpWithNaviga (below) w tej instrukcji warunkowej
        //2. Pomysł na to by bottomNavigationView było domyslnie ukryte i dopiero po logowaniu było dokryte
        bottomNavigationView.setupWithNavController(navController)
    }
}
//Zastanowić się jak dodać to LoginActivity, by jako pierwsze było jeśli użytkownik nie jest zalogowany - zakładaka z StackOverFlow
//Następnie View i Data Binding dodać
//Następnie LogowanieGoogle z MVVM itd.