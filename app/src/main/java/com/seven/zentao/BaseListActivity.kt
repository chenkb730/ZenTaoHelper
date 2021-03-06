package com.seven.zentao

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 * Created by Seven on 2018/1/26.
 */
abstract class BaseListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        refreshLayout.setOnRefreshListener {
            loadData()
        }

        actionBtn.setOnClickListener {
            if (!refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = true
                loadData()
            }
        }

        refreshLayout.isRefreshing = true
        loadData()
    }


    abstract fun loadData()

    fun refreshComplete() {
        if (refreshLayout.isRefreshing) {
            refreshLayout.isRefreshing = false
        }
    }

}