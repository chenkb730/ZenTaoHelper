package com.seven.zentao.http

import com.hazz.kotlinmvp.net.RetrofitManager
import io.reactivex.Observable

/**
 * api请求
 * Created by Seven on 2018/1/25.
 */
object ZeoTaoApi {


    fun loginZeoTao(username: String, password: String): Observable<String> {
        return RetrofitManager.service.login(username, password)
    }

    fun myBugs(): Observable<String> {
        return RetrofitManager.service.myBugs()
    }

    fun resolvedBy(): Observable<String> {
        return RetrofitManager.service.resolvedBy()
    }
}