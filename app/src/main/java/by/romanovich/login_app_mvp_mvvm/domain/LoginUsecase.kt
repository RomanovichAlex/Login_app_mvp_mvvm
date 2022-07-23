package by.romanovich.login_app_mvp_mvvm.domain

import androidx.annotation.MainThread

//iterator
//предметная облость, уже несет данные
//выносим работу с многопоточностью

interface LoginUsecase {

    fun login(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )

    fun forgotPassword(
        username: String,
        @MainThread callback: (String) -> Unit
    )

    fun addAccount(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )

    fun checkConnect(): Boolean
    fun checkAccount(login: String): Boolean
}