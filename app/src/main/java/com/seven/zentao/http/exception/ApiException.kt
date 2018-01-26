package com.hazz.kotlinmvp.net.exception

/**
 * Created by Seven on 2018/1/4.
 *
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}