package by.romanovich.login_app_mvp_mvvm.domain

//model
interface LoginApi {
    //функция принимает login и password и возращает Boolean
    fun login(login:String, password: String): Boolean
    //функция передает login,email и password и возращает Boolean
    fun register(login:String, password: String, email: String): Boolean
    //функция сервер в курсе что клиент ушел
    fun logout(): Boolean
    //функция забыли пароль
    fun forgotPassword(login:String): Boolean
}