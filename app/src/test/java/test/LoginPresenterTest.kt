package test

import by.romanovich.login_app_mvp_mvvm.data.loginusecase.LoginUsecaseImpl
import by.romanovich.login_app_mvp_mvvm.ui.login.LoginContract
import by.romanovich.login_app_mvp_mvvm.ui.login.LoginPresenter
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

    private lateinit var presenter: LoginPresenter

    @Mock
    private lateinit var loginUsecase: LoginUsecaseImpl

    @Mock
    private lateinit var view: LoginContract.View


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = LoginPresenter(loginUsecase)
    }

    @Test
    fun onLogin_checkConnect_true_Test() {
        val login = "admin"
        val password = "admin"
        Mockito.`when`(loginUsecase.checkConnect()).thenReturn(true)

        presenter.onLogin(login, password)
        verify(loginUsecase).checkConnect()
    }


    @Test
    fun onForgotPassword_checkConnect_true_Test() {
        val login = "admin"
        Mockito.`when`(loginUsecase.checkConnect()).thenReturn(true)

        presenter.onForgotPassword(login)
        verify(loginUsecase).checkConnect()
    }


    @Test
    fun onSingUp_checkConnect_true_Test() {
        val login = "admin"
        val password = "admin"
        Mockito.`when`(loginUsecase.checkConnect()).thenReturn(true)

        presenter.onSignUp(login, password)
        verify(loginUsecase).checkConnect()
    }
}