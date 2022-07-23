package by.romanovich.login_app_mvp_mvvm.ui.login

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import by.romanovich.login_app_mvp_mvvm.app
import by.romanovich.login_app_mvp_mvvm.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private var presenter : LoginContract.Presenter? = null
    private var viewModel : LoginContract.ViewModel? = null

    private val handler:Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compositeDisposable = CompositeDisposable()
        viewModel = restoreViewModel()
        //viewModel?.onAttach(this)

        binding.loginButton.setOnClickListener {
            viewModel?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        //подписываемся
        viewModel?.let {
            compositeDisposable.add(it.shouldShowProgress
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { shouldShow ->
                    if (shouldShow == true) {
                        showProgress()
                    } else {
                        hideProgress()
                    }
                })
        }

        viewModel?.isSuccess?.subscribe(handler) {
            if (it == true) {
                setSuccess()
            }
        }
        viewModel?.errorText?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(it)
                }
            }
        }
    }


    //отписываемся
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        viewModel?.isSuccess?.unsubscribeAll()
        viewModel?.errorText?.unsubscribeAll()
    }


    //метод что бы достать презентор(объект) сохраненный в lastCustomNonConfigurationInstance если он LoginPresenter то мы его сохраняем
    private fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        /*для запоминания при повороте, перенесли в класс апп
        val api = (application as App).api*/
        //возращаем presenter, но если он ноль то создаем новый презентер
        return viewModel?: LoginViewModel(app.loginUsecase)
    }

    //метод что бы положить объект
    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }


    private fun setSuccess() {
            binding.loginButton.isVisible = false
            binding.loginEditText.isVisible = false
            binding.passwordEditText.isVisible = false
            binding.root.setBackgroundColor(Color.GREEN)
    }

    private fun setError(error: String) {
        Toast.makeText(this, " " +
                "$error", Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
    }

    private fun hideProgress() {
            binding.loginButton.isEnabled = true
    }

    /*override fun getHandler(): Handler {
   return Handler(Looper.getMainLooper())
    }*/


    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}