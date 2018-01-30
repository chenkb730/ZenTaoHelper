package com.seven.zentao.contract

import android.content.Context
import com.seven.zentao.module.User

/**
 * Created by Seven on 2018/1/26.
 */
class LoginContract {


    interface ILoginView {

        fun startLogin()
        fun stopLogin()
        fun loginInfoEmpty(message: String)

        fun loginSuccess(user: User)

        fun loginError(message: String)
    }

    interface ILoginPresenter {
        fun doLogin(userName: String, password: String)

        fun checkLogin(context: Context)
    }
}