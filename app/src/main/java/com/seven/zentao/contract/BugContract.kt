package com.seven.zentao.contract

import android.content.Context
import com.seven.zentao.config.BUGENUM
import com.seven.zentao.module.BugInfo

/**
 * Created by Seven on 2018/1/29.
 */
class BugContract {

    interface IBugView {
        fun bugList(list: List<BugInfo>)

        fun bugEmpty()

        fun requestStop()


    }


    interface IBugPresenter {
        fun requestBugs(context: Context, enum: BUGENUM)
    }
}