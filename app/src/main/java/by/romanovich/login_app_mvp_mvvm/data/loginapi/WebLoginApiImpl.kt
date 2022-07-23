package by.romanovich.login_app_mvp_mvvm.data.loginapi

import by.romanovich.login_app_mvp_mvvm.domain.LoginApi

class WebLoginApiImpl : LoginApi {
    override fun login(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(username: String): String {
        TODO("Not yet implemented")
    }

    override fun checkAccount(username: String): Boolean {
        TODO("Not yet implemented")
    }
}