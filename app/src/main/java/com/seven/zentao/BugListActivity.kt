package com.seven.zentao

import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.seven.zentao.adapter.BugListAdapter
import com.seven.zentao.config.BUGENUM
import com.seven.zentao.contract.BugContract
import com.seven.zentao.module.BugInfo
import com.seven.zentao.presenter.BugPresenter
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 * bug 列表
 * Created by Seven on 2018/1/26.
 */
class BugListActivity : BaseListActivity(), BugContract.IBugView {
    private var adapter: BugListAdapter? = null
    private var bugPresenter: BugContract.IBugPresenter? = null
    override fun loadData() {
        bugPresenter = bugPresenter ?: BugPresenter(this)
        bugPresenter!!.requestBugs(this, BUGENUM.ALL)
    }

    override fun bugList(list: List<BugInfo>) {

        adapter = adapter ?: BugListAdapter(this, datas = list)
        recyclerView.adapter = adapter

    }

    override fun bugEmpty() {
        Snackbar.make(this.window.decorView, "bug is empty", Snackbar.LENGTH_LONG).show()
    }

    override fun requestStop() {
        refreshComplete()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bug_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        refreshLayout.isRefreshing = true
        when (item?.itemId) {
            R.id.bugs -> {
                bugPresenter!!.requestBugs(this, BUGENUM.ALL)
            }

            R.id.resolvedBy -> {
                bugPresenter!!.requestBugs(this, BUGENUM.RESOLVEBY)

            }
            R.id.assignedTo -> {
                bugPresenter!!.requestBugs(this, BUGENUM.ASSIGNEDTO)
            }

            R.id.closedBy -> {
                bugPresenter!!.requestBugs(this, BUGENUM.CLOSEDBY)
            }
            R.id.openedBy -> {
                bugPresenter!!.requestBugs(this, BUGENUM.OPENEDBY)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}