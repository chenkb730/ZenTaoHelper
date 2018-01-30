package com.seven.zentao.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.seven.zentao.model.BugRepository
import com.seven.zentao.module.BugInfo

/**
 * Created by Seven on 2018/1/29.
 */
class BugViewModel : ViewModel() {

    private val bugRepository = BugRepository()
    private val bugLiveData = MutableLiveData<List<BugInfo>>()


    fun getBugLists(html: String): MutableLiveData<List<BugInfo>> {
        bugRepository.receiveBugList(html) { it -> bugLiveData.postValue(it) }
        return bugLiveData
    }

}