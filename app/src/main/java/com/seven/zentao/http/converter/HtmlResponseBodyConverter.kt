package com.seven.zentao.http.converter

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 *
 * Created by Seven on 2018/1/25.
 */
class HtmlResponseBodyConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody?): String {
        return value?.string()!!
    }

}