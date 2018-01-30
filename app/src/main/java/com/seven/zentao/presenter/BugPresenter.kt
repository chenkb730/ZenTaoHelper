package com.seven.zentao.presenter

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.FragmentActivity
import com.seven.zentao.config.BUGENUM
import com.seven.zentao.contract.BugContract
import com.seven.zentao.http.ZenTaoApi
import com.seven.zentao.viewmodel.BugViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * bug presenter
 * Created by Seven on 2018/1/29.
 */
class BugPresenter constructor(private val mView: BugContract.IBugView) : BugContract.IBugPresenter {

    private var request: Observable<String>? = null
    override fun requestBugs(context: Context, enum: BUGENUM) {

        request = when (enum) {
            BUGENUM.ALL -> ZenTaoApi.myBugs()

            BUGENUM.ASSIGNEDTO -> ZenTaoApi.assignedTo()

            BUGENUM.CLOSEDBY -> ZenTaoApi.closedBy()

            BUGENUM.OPENEDBY -> ZenTaoApi.openedBy()

            BUGENUM.RESOLVEBY -> ZenTaoApi.resolvedBy()

        }

        request = request ?: ZenTaoApi.myBugs()

        val bugViewModel = ViewModelProviders.of(context as FragmentActivity).get(BugViewModel::class.java)
        request!!.flatMap {
            Observable.just(bugViewModel.getBugLists(it))
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView.requestStop()
                    if (it.value!!.isNotEmpty()) {
                        mView.bugList(it.value!!)
                    } else {
                        mView.bugEmpty()
                    }
                })
    }
}