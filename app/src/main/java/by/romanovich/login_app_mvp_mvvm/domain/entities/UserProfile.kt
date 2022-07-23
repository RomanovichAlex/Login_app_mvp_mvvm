package by.romanovich.login_app_mvp_mvvm.domain.entities

//когда в одном приложении может быть много пользователей
//например возращает логин
data class UserProfile(
    val login: String,
    val password: String,
    val email: String = "www.mail@mail.com",
    val avatarUrl: String = "www.avatar@mail.com",
    val age: Int = 25,
)