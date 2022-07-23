package by.romanovich.login_app_mvp_mvvm.domain

import androidx.annotation.WorkerThread

//предметная облость, достает данные
interface LoginApi {
    //рабочий поток
    @WorkerThread
    //функция принимает login и password и возращает Boolean
    fun login(username: String, password: String): Boolean

    @WorkerThread
    //функция передает login,email и password и возращает Boolean
    fun register(username: String, password: String): Boolean

    @WorkerThread
    fun checkAccount(username: String): Boolean

    @WorkerThread
    //функция забыли пароль
    fun forgotPassword(username: String): String
}