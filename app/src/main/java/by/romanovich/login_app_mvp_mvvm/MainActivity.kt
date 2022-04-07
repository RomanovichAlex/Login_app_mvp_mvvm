package by.romanovich.login_app_mvp_mvvm

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import by.romanovich.login_app_mvp_mvvm.databinding.ActivityMainBinding
import java.util.logging.Handler

class MainActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter : LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    //метод что бы достать презентор(объект) сохраненный в lastCustomNonConfigurationInstance если он LoginPresenter то мы его сохраняем
    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
       //возращаем presenter, но если он ноль то создаем новый презентер
        return presenter ?: LoginPresenter()
    }

    //метод что бы положить объект
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

@MainThread
    override fun setSuccess() {
            binding.loginButton.isVisible = false
            binding.loginEditText.isVisible = false
            binding.passwordEditText.isVisible = false
            binding.root.setBackgroundColor(Color.GREEN)
    }
    @MainThread
    override fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
    }
    @MainThread
    override fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
    }
    @MainThread
    override fun hideProgress() {
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