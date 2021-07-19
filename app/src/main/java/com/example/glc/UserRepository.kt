package com.example.glc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository : LiveData<FirebaseUser?>() {

    private val TAG = "UserRepository"

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

                            authUserMutableLiveData.postValue(user)

                            userRef.add(user)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "Created User")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Didn't create user $e")
                                }
                        }
                    }
                }
            } else {
                Log.e(TAG, "Error = ${authTask.exception?.message} ")
            }
        }
        return authUserMutableLiveData
    }

    fun createNewUser(authenticatedUser: User): MutableLiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        val uidRef = userRef.document(authenticatedUser.uid)
        uidRef.get().addOnCompleteListener { uidTask ->
            if (uidTask.isSuccessful) {
                val document: DocumentSnapshot? = uidTask.result
                if (document != null) {
                    if (document.exists())
                        newUserMutableLiveData.postValue(authenticatedUser)
                } else {
                    uidRef.set(authenticatedUser).addOnCompleteListener { userCreationTask ->
                        if (userCreationTask.isSuccessful) {
                            newUserMutableLiveData.postValue(authenticatedUser)
                        } else {
                            Log.e(TAG, "Error CreateNewUser ${userCreationTask.exception?.message}")
                        }
                    }
                }
            }
        }
        return newUserMutableLiveData
    }
}

//Żeby prawdziwie wylogowyało użytkowników, należy googleSignInClient i wtedy singOut
//1. !!!CHECKED!!! - Spróbować wpierw użyć singletona, na tej zmiennej aby była jedna i niepowtarzalna
//3. Zrobić to main UI, aby było niewidczone przy LoginFragment
//5. !!!CHECKED!!! - Zastanowić się nad tymi animacjami w Navigation..., by przechodziło gdy chodzi o Wylogowywanie
//6. 6.1 GET pobrać dane z firestore na temat użytkowników - 6.2 i sprawdzic (?exists) czy UID jest już wykorzystywane jako nazwa Dokumentu
//4. Slide -> <- między kartami już na głównym ekranie z BottomMenu
//2. Poprawić ogólnie kod w Repo i ViewModel i LoginFragment
