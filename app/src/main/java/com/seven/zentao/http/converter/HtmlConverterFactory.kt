package com.seven.zentao.http.converter

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by Seven on 2018/1/25.
 */
class HtmlConverterFactory : Converter.Factory() {

    companion object {
        fun create(): HtmlConverterFactory {
            return HtmlConverterFactory()
        }
    }


    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *> {
        return HtmlResponseBodyConverter()
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody> {
        return HtmlRequestBodyConverter()
    }

}