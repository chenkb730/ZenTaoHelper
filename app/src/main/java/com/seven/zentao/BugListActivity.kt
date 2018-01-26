package com.seven.zentao

import android.util.Log
import com.seven.zentao.adapter.BugListAdapter
import com.seven.zentao.config.ZenTaoRegex
import com.seven.zentao.http.ZeoTaoApi
import com.seven.zentao.module.BugInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 * Created by Seven on 2018/1/26.
 */
class BugListActivity : BaseListActivity() {


    override fun loadData() {

        ZeoTaoApi.resolvedBy().flatMap {
            val bugList = arrayListOf<BugInfo>()
            Regex(ZenTaoRegex.TAG_TR).findAll(it).forEach {
                Regex(ZenTaoRegex.TAG_BUD_DES).findAll(it.value).forEach {
                    val bugInfo = BugInfo()
                    bugInfo.bugInfo = it.value

                    Log.d("test", bugInfo.bugInfo)
                    bugList.add(bugInfo)
                }
            }
            Observable.just(bugList)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val adapter = BugListAdapter(this@BugListActivity, datas = it)
                    recyclerView.adapter = adapter
                })
    }
}