package by.romanovich.login_app_mvp_mvvm.data.loginapi

import by.romanovich.login_app_mvp_mvvm.domain.LoginApi
import by.romanovich.login_app_mvp_mvvm.domain.entities.UserProfile
import by.romanovich.login_app_mvp_mvvm.utils.ErrorCodes

class MockLoginApiImpl : LoginApi {

    private val mockRepo = mutableSetOf(
        UserProfile("admin", "admin"),
        UserProfile("user", "1234"),
    )

    override fun login(username: String, password: String): Boolean {
        Thread.sleep(3_000)
        return checkCredentials(username, password)
    }

    override fun register(username: String, password: String): Boolean {
        Thread.sleep(2_000)
        mockRepo.add(UserProfile(username, password))
        return true
    }

    override fun forgotPassword(username: String): String {
        Thread.sleep(1_000)
        mockRepo.forEach { user ->
            if (user.login == username) return user.password
        }
        return ErrorCodes.NO_FOUND.textError
    }

    override fun checkAccount(username: String): Boolean {
        mockRepo.forEach { user ->
            if (user.login == username) return true
        }
        return false
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        mockRepo.forEach { user ->
            if (user.login == login && user.password == password) return true
        }
        return false
    }
}