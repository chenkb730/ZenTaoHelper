package com.seven.zentao.presenter

import android.content.Context
import android.text.TextUtils
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.seven.zentao.contract.LoginContract
import com.seven.zentao.http.ZenTaoApi
import com.seven.zentao.http.cookie.PersistentCookieStore
import com.seven.zentao.module.User
import com.seven.zentao.utils.MD5Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Seven on 2018/1/26.
 */
class LoginPresenter constructor(private val mView: LoginContract.ILoginView?) : LoginContract.ILoginPresenter {
    override fun doLogin(userName: String, password: String) {

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mView?.loginInfoEmpty("userName or password is Empty")
            return
        }
        mView?.startLogin()
        ZenTaoApi.loginZeoTao(userName, MD5Util.getMD5Encoding(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { mView?.stopLogin() }
                .subscribe({
                    if (it.contains("index.html")) {
                        mView?.loginSuccess(User())
                    } else {
                        mView?.loginError("登录失败")
                    }
                }, {
                    it.printStackTrace()
                    val exceptionMessage = ExceptionHandle.handleException(it)
                    mView?.loginError(exceptionMessage)
                })
    }

    override fun checkLogin(context: Context) {
        val cookieStore = PersistentCookieStore(context)
        if (cookieStore.getCookies().isNotEmpty()) {
            mView!!.loginSuccess(User())
        }

    }

}