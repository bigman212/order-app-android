package org.application.bigman.fogstreamorderapp.orderlist

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.orderdetail.OrderDetailActivity

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 20.02.2018.
 **/

class OrderListAdapter(private val mContext: Context) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    private var mItems: List<Order> = arrayListOf()

    fun updateData(orders: List<Order>) {
        mItems = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, viewGroup, false)
        val holder = ViewHolder(view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var clickPosition = 0

        fun bindForecast(order: Order) {
            itemView.tv_order_date.text = order.dateOfOrderCreation //TODO (models)
            itemView.tv_order_from.text = order.startAddress!!.address
            itemView.tv_order_to.text = order.endAddress!!.address
            clickPosition = order.id!!
            itemView.setOnClickListener {
                //КОСТЫЛЬ TODO
                val intent = Intent(it.context, OrderDetailActivity::class.java)
                intent.putExtra("orderId", this.clickPosition)
                it.context.startActivity(intent)
            }
        }
    }
}
