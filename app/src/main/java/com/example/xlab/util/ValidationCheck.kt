package com.example.xlab.util

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation{
    if (email.isEmpty())
        return RegisterValidation.Failed("Поле не может быть пустым")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Неправильный формат почты")

    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation{
    if (password.isEmpty())
        return RegisterValidation.Failed("Поле не мжоет быть пустым")
    if (password.length < 6)
        return RegisterValidation.Failed("Пароль должен содержать 6 знаков")

    return RegisterValidation.Success

}
