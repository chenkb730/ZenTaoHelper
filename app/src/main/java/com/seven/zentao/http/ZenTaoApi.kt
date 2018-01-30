package com.seven.zentao.http

import com.hazz.kotlinmvp.net.RetrofitManager
import io.reactivex.Observable

/**
 * api请求
 * Created by Seven on 2018/1/25.
 */
object ZenTaoApi {


    fun loginZeoTao(username: String, password: String): Observable<String> {
        return RetrofitManager.service.login(username, password)
    }

    fun myBugs(): Observable<String> {
        return RetrofitManager.service.myBugs()
    }

    fun resolvedBy(): Observable<String> {
        return RetrofitManager.service.resolvedBy()
    }

    //指派给我
    fun assignedTo(): Observable<String> {
        return RetrofitManager.service.assignedTo()
    }

    //由我创建
    fun openedBy(): Observable<String> {
        return RetrofitManager.service.openedBy()
    }

    //由我关闭
    fun closedBy(): Observable<String> {
        return RetrofitManager.service.closedBy()
    }
}