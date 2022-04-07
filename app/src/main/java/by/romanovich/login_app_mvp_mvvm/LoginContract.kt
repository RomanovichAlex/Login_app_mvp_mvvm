package by.romanovich.login_app_mvp_mvvm

import android.view.View
import androidx.annotation.MainThread
import java.util.logging.Handler

//контракт для всех сущностей
class LoginContract {

    //все методы view
    interface View {
        @MainThread
        fun setSuccess()
        @MainThread
        fun setError(error: String)
        @MainThread
        fun showProgress()
        @MainThread
        fun hideProgress()
        //fun getHandler(): Handler
    }

    //все методы пресентера
    interface Presenter{
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()
    }
}