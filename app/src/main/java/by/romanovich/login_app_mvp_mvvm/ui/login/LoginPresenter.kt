package by.romanovich.login_app_mvp_mvvm.ui.login

import android.os.Handler
import android.os.Looper
import by.romanovich.login_app_mvp_mvvm.domain.LoginApi
import by.romanovich.login_app_mvp_mvvm.data.MockLoginApiImpl

class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    //для запоминания при перевороте
    private var isSuccess: Boolean = false
    private var errorText: String = ""
    //получаем MockLoginApiImpl
    private val api: LoginApi = MockLoginApiImpl()


    override fun onAttach(view: LoginContract.View) {
        this.view = view
        //для сохранения изменений при перевороте
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            //дождаться результат
            val succes=api.login(login, password)
            //вызываем настоящий вызов
            api.login(login, password)
            uiHandler.post {
                view?.hideProgress()
                if (succes) {
                    //if (checkCredentials(login, password)) { БЫЛО
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                } else {
                    view?.setError("Неверный пароль!!!")
                    isSuccess = false
                    errorText = "Неверный пароль!!!"
                }
            }
        }.start()
    }

    /*private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }было*/

    override fun onCredentialsChanged() {
        // todo
    }
}
