package com.seven.zentao.model

import android.util.Log
import com.seven.zentao.module.BugInfo
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.jsoup.Jsoup

/**
 * bug数据处理
 * Created by Seven on 2018/1/29.
 */

typealias  bugCommend = (List<BugInfo>) -> Unit

class BugRepository {

    fun receiveBugList(html: String, bugCommend: bugCommend): Disposable {

        return Observable.just(html).map {
            parseBug(it)
        }.subscribe({
            bugCommend.invoke(it)
        }, { it.printStackTrace() }

        )
    }

    private fun parseBug(html: String): List<BugInfo> {
        val bugInfos = arrayListOf<BugInfo>()

        if (html.isNotEmpty()) {
            val document = Jsoup.parse(html)
            Log.d("jsoup", "bugList:${document.body()}")
            //获取根节点
            val bugDocument = document.getElementById("bugList")
            Log.d("jsoup", "bugList:$bugDocument")

            val bugElements = bugDocument.getElementsByClass("text-left nobr")



            bugElements.forEach {
                val bugInfo = BugInfo()
                bugInfo.bugInfo = it.select("a").text()
                bugInfo.bugUrl = it.select("a").`val`()

                bugInfos.add(bugInfo)
            }
        }
        return bugInfos
    }
}