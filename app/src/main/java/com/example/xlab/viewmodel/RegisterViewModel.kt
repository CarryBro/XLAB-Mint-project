package com.example.xlab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xlab.data.User
import com.example.xlab.util.Constants.USER_COLLECTION
import com.example.xlab.util.RegisterFieldState
import com.example.xlab.util.RegisterValidation
import com.example.xlab.util.Resource
import com.example.xlab.util.validateEmail
import com.example.xlab.util.validatePassword
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore

): ViewModel() {

    private val _register = MutableStateFlow<Resource<User>> (Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    public val validation = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(user: User, password: String) {
        if (checkValidation(user, password)) {
            viewModelScope.launch {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        saveUserInfo(it.uid, user)

                        }

                }.addOnFailureListener {
                        _register.value = Resource.Error(it.message.toString())

                }
        } else { // This else now correctly corresponds to the if(checkValidation(...))
            val registerFieldState = RegisterFieldState(
                validateEmail(user.email),
                validatePassword(password)
            )
            viewModelScope.launch {
                _validation.send(registerFieldState)
            }
        }
    }

    private fun saveUserInfo(userUid: String, user: User) {
        db.collection(USER_COLLECTION)
            .document(userUid)
            .set(user)
            .addOnSuccessListener{
                viewModelScope.launch(Dispatchers.Main) {
                    _register.value = Resource.Success(user)
                }
            }.addOnFailureListener{
                viewModelScope.launch(Dispatchers.Main) {
                    _register.value = Resource.Error(it.message.toString())
                }
            }
    }

    private fun checkValidation(user: User, password: String):Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}


