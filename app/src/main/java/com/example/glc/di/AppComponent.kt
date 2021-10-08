package com.example.glc.di

import com.example.glc.AppModule
import com.example.glc.MainActivity
import com.example.glc.add.AddFragment
import com.example.glc.add.di.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: AddFragment)
}
/*
https://github.com/android/architecture-samples/blob/dev-dagger/app/src/main/java/com/example/android/architecture/blueprints/todoapp/TodoApplication.kt
https://www.youtube.com/watch?v=nRQVlaEKYkU&ab_channel=SimplifiedCoding
https://developer.android.com/training/dependency-injection/dagger-android
https://www.youtube.com/watch?v=EF33KmyprEQ&t=4210s&ab_channel=PhilippLackner

 */