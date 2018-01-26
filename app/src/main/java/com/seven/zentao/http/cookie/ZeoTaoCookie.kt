package com.seven.zentao.http.cookie

import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by Seven on 2018/1/26.
 */
class ZeoTaoCookie(context: Context) : CookieJar {


    var cookieStore: PersistentCookieStore? = null


    private var map: HashMap<String, MutableList<Cookie>>? = hashMapOf()

    init {
        cookieStore = PersistentCookieStore(context)
    }


    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        cookies?.forEach({ cookieStore!!.add(url!!, it) })


    }

    override fun loadForRequest(url: HttpUrl?): List<Cookie> {
        return cookieStore!![url!!] ?: arrayListOf()
    }


}