package by.romanovich.login_app_mvp_mvvm.ui.login

import android.os.Handler
import androidx.annotation.MainThread

//контракт для всех сущностей
class LoginContract {

    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showLoading()

        @MainThread
        fun hideLoading()

        @MainThread
        fun getHandler(): Handler

        fun passwordReminderSuccess(password: String)
        fun addAccountSuccess(login: String)
    }

    interface Presenter {
        @MainThread
        fun onAttach(view: View)

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onForgotPassword(login: String)

        @MainThread
        fun onSignUp(login: String, password: String)
    }
}