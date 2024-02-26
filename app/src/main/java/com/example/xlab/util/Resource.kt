    package com.example.xlab.util
    import com.example.xlab.data.User
    import kotlinx.coroutines.flow.StateFlow

    sealed class Resource<T>(
        val data: StateFlow<Resource<User>>? =null,
        val message: String?=null
    ){
        class Success<T>(data: StateFlow<Resource<User>>): Resource<T>(data)
        class Error<T>(message: String):Resource<T>(message = message)
        class Loading<T>: Resource<T>()
        class Unspecified<T>: Resource<T>()
    }