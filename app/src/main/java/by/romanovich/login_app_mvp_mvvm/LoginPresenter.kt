package by.romanovich.login_app_mvp_mvvm

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep

class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    //для запоминания при перевороте
    private var isSuccess: Boolean = false
    private var errorText: String = ""

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
            sleep(3_000)
            uiHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
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

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialsChanged() {
        // todo
    }
}
