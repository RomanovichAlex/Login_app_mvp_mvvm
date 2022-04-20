package by.romanovich.login_app_mvp_mvvm.ui.login

import androidx.annotation.MainThread

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