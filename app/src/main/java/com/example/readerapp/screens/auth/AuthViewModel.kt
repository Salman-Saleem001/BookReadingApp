package com.example.readerapp.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AuthViewModel : ViewModel() {
    //   val loadingState  = MutableStateFlow(LoadingState.IDLE)
    private val auth = Firebase.auth

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun signInWithEmailAndPassword(email: String, password: String, moveTo: () -> Unit) =     viewModelScope.launch {
        try {
            _loading.value= true
            Log.d("DEBUG", "Loading set to false: ${_loading.value}")

            delay(2000)
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Log.d("FB", "signInWithEmailAndPassword: Success, $result")
            if (result!=null){
                moveTo.invoke()
            }
        } catch (ex: Exception) {
            Log.d("FB", "signInWithEmailAndPassword: Exception caught, ${ex.message}")
        } finally {
            _loading.value= false
            Log.d("DEBUG", "Loading set to false: ${_loading.value}")
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, moveTo: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.value = true
                delay(2000)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            moveTo.invoke()
                        }
                        _loading.value = false
                        Log.d("FB", "createUserWithEmailAndPassword: ${task.result}")
                    }
            } catch (ex: Exception) {  // Catch all exceptions
                _loading.value = false
                Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
            }
        }

//    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        Log.d("FB", "Coroutine Exception: ${throwable.message}")
//        _loading.postValue(false)
//    }
}