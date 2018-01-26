package com.seven.zentao

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Seven on 2018/1/25.
 */
class ZenTaoApplication : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }
}