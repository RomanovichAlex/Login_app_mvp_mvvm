package by.romanovich.login_app_mvp_mvvm

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import by.romanovich.login_app_mvp_mvvm.data.loginapi.MockLoginApiImpl
import by.romanovich.login_app_mvp_mvvm.data.loginusecase.LoginUsecaseImpl
import by.romanovich.login_app_mvp_mvvm.domain.LoginApi
import by.romanovich.login_app_mvp_mvvm.domain.LoginUsecase


//создаём апи на все приложение единожды
class App : Application() {
    //никто кроме активити не знает о существовании loginApi
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }
}

//нужен чтобы получить LoginUsecase
val Context.app: App
    get() {
        return applicationContext as App
    }
