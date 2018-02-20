package org.application.bigman.fogstreamorderapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.recycler_item.*


class OrderListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        rv_orders.layoutManager = LinearLayoutManager(this)
        rv_orders.hasFixedSize()
        rv_orders.adapter = MyAdapter(getLists())
    }

    fun getLists(): ArrayList<Order> {
        var lists = ArrayList<Order>()
//        lists.add(Order("YES", 1))
        lists.add(Order("NO", 2))
        lists.add(Order("QWERTY", 3))
        return lists
    }

}
