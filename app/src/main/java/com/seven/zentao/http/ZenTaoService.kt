package com.seven.zentao.http

import com.seven.zentao.module.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 接口服务
 * Created by Seven on 2018/1/25.
 */
interface ZenTaoService {

    @POST("/zentao/user-login.html")
    fun login(@Query("account") username: String, @Query("password") password: String): Observable<String>

    @GET("/zentao/my-bug.html")
    fun myBugs(): Observable<String>

    @GET("/zentao/my-bug-resolvedBy.html")
    fun resolvedBy(): Observable<String>
}