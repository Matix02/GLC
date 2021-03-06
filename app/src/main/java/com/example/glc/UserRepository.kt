package com.example.glc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository : LiveData<FirebaseUser?>() {

    companion object {
        private const val TAG = "UserRepository"
    }

    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val userRef: CollectionReference = rootRef.collection("users")

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        value = firebaseAuth.currentUser
    }

    override fun onActive() { firebaseAuth.addAuthStateListener(authStateListener) }

    override fun onInactive() { firebaseAuth.removeAuthStateListener(authStateListener) }

    fun firebaseSingInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User> {
        val authUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential).addOnCompleteListener { authTask ->
            if (authTask.isSuccessful) {
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val uid = firebaseUser.uid
                    val name = firebaseUser.displayName
                    val email = firebaseUser.email

                    if (name != null) {
                        if (email != null) {
                            val user = User(uid, name, email)

                            checkNCreateUser(user)

                            authUserMutableLiveData.postValue(user)
                        }
                    }
                }
            } else {
                Log.e(TAG, "Error = ${authTask.exception?.message} ")
            }
        }
        return authUserMutableLiveData
    }

    private fun addUser(user: User) {
        userRef.add(user)
            .addOnSuccessListener {
                Log.d(TAG, "Created User")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Didn't create user $e")
            }
    }

    private fun checkNCreateUser(user: User) {
        userRef.whereEqualTo("uid", user.uid)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty){
                    addUser(user)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}

//??eby prawdziwie wylogowya??o u??ytkownik??w, nale??y googleSignInClient i wtedy singOut
//1. !!!CHECKED!!! - Spr??bowa?? wpierw u??y?? singletona, na tej zmiennej aby by??a jedna i niepowtarzalna
//3. !!!CHECKED!!! - Zrobi?? to main UI, aby by??o niewidczone przy LoginFragment
//5. !!!CHECKED!!! - Zastanowi?? si?? nad tymi animacjami w Navigation..., by przechodzi??o gdy chodzi o Wylogowywanie
//6. !!!CHECKED!!! - 6.1 GET pobra?? dane z firestore na temat u??ytkownik??w - 6.2 i sprawdzic (?exists) czy UID jest ju?? wykorzystywane jako nazwa Dokumentu
//2. !!!CHECKED!!! - Poprawi?? og??lnie kod w Repo i ViewModel i LoginFragment
//4. *P????niej* - Slide -> <- mi??dzy kartami ju?? na g????wnym ekranie z BottomMenu
