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

    //所有
    @GET("/zentao/my-bug.html")
    fun myBugs(): Observable<String>

    //由我解决
    @GET("/zentao/my-bug-resolvedBy.html")
    fun resolvedBy(): Observable<String>

    //指派给我
    @GET("/zentao/my-bug-assignedTo.html")
    fun assignedTo(): Observable<String>

    //由我创建
    @GET("/zentao/my-bug-openedBy.html")
    fun openedBy(): Observable<String>

    //由我关闭
    @GET("/zentao/my-bug-closedBy.html")
    fun closedBy(): Observable<String>
}