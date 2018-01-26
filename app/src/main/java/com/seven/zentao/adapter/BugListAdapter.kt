package com.seven.zentao.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import com.seven.zentao.R

import com.seven.zentao.module.BugInfo
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created by Seven on 2018/1/26.
 */

class BugListAdapter(context: Context, layoutId: Int = R.layout.item_bug_list, datas: List<BugInfo>) : CommonAdapter<BugInfo>(context, layoutId, datas) {

    override fun convert(holder: ViewHolder, bugInfo: BugInfo, position: Int) {
        holder.getView<AppCompatTextView>(R.id.bugInfo).text = bugInfo.bugInfo
    }
}
