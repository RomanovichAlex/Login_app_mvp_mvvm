package by.romanovich.login_app_mvp_mvvm.ui.login


import androidx.annotation.MainThread
import by.romanovich.login_app_mvp_mvvm.utils.Publisher
import io.reactivex.rxjava3.core.Observable

//model
//контракт для всех сущностей
class LoginContract {

    /**
     * MVP  - Model View Presenter
     * 1) Восстановление состояния(при поворотах, moxy, у нас success)
     * 2) Большая* связность(view знает о presentere)
     * 3) Многословность - проверки на нулл, постоянные вызовы вью
     *
     * (M) <- (P) <-> (V)
     *
     * MVVM - Model View ViewModel
     * (M) <- (VM) <- (V)
     */

    //    Интерфейс вью больше не нужен в MVVM
//    Её роль достаётся подпискам во ViewModel
    //переходим в viewModel
    //все методы view
    //    interface View {
//        @MainThread
//        fun setSuccess() // isSuccess
//
//        @MainThread
//        fun setError(error: String)
//
//        @MainThread
//        fun showProgress() // shouldShowProgress
//
//        @MainThread
//        fun hideProgress() // shouldShowProgress
//    }


    //подписка на изменение viewModel
    /**
     * class Activity {
     *
     *  fun onCreate() {
     *      viewModel.shouldShowProgress.subscribe { it ->
     *          if (it) {
     *              dialog.show()
     *          } else {
     *              dialog.dismiss()
     *          }
     *      }
     *  }
     *
     * }
     */


    //все методы пресентера
    //interface Presenter{
    interface ViewModel{
        //@MainThread
        //fun onAttach(view: View)

//Publisher - переменные которые публикуют
        //вместо view
        //val - не изменяемые переменнные
        val shouldShowProgress: Observable<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)
        @MainThread
        fun onCredentialsChanged()
    }
}