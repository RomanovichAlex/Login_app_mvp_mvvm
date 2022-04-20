package by.romanovich.login_app_mvp_mvvm.data

import by.romanovich.login_app_mvp_mvvm.domain.LoginApi

//тестовый
class MockLoginApiImpl : LoginApi {

    override fun login(login: String, password: String): Boolean {
       //будет валить приложение если не из главного потока вызвана
        Thread.sleep(3_000)
        return login == password
    }

    override fun register(login: String, password: String, email: String): Boolean {
        Thread.sleep(2_000)

        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(2_000)
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread.sleep(1_000)
        return false
    }
}