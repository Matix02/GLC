package com.example.glc

import android.app.Application
import com.example.glc.di.AppComponent
import com.example.glc.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initalizeComponent()
    }

    private fun initalizeComponent(): AppComponent {
        return DaggerAppComponent.create()
    }
}
/*
https://www.youtube.com/watch?v=Xd7dOKonYJ0&ab_channel=CodingWithMitch
https://www.youtube.com/watch?v=y5z1ZaSZqcI&ab_channel=LearningWorldz
https://www.youtube.com/watch?v=_B0skaOiVCU&ab_channel=LearningWorldz
*/
