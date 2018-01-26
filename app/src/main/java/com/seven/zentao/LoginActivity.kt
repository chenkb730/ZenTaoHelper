package com.seven.zentao

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.seven.zentao.contract.LoginContract
import com.seven.zentao.module.User
import com.seven.zentao.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), LoginContract.ILoginView {

    private var loginPresenter: LoginContract.ILoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenter(this)

        loginBtn.setOnClickListener {
            loginPresenter!!.doLogin(username.text.toString(), password.text.toString())
        }
    }

    override fun startLogin() {
        showProgress(true)
    }

    override fun stopLogin() {
        showProgress(false)
    }

    override fun loginInfoEmpty(message: String) {
        Snackbar.make(this.window.decorView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun loginSuccess(user: User) {
        startActivity(Intent(this, BugListActivity::class.java))
    }

    override fun loginError(message: String) {
        Snackbar.make(this.window.decorView, message, Snackbar.LENGTH_LONG).show()
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }


}
