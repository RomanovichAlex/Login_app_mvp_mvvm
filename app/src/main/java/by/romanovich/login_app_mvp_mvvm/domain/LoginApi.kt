package by.romanovich.login_app_mvp_mvvm.domain

import androidx.annotation.WorkerThread

//model
interface LoginApi {
    @WorkerThread
    fun login(username: String, password: String): Boolean

    @WorkerThread
    fun register(username: String, password: String): Boolean

    @WorkerThread
    fun forgotPassword(username: String): String

    @WorkerThread
    fun checkAccount(username: String): Boolean
}