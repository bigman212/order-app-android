package org.application.bigman.fogstreamorderapp.orderlist

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.application.bigman.fogstreamorderapp.R
import org.application.bigman.fogstreamorderapp.data.Constants
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.orderdetail.OrderDetailActivity


/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 20.02.2018.
 **/

class OrderListAdapter(private val mContext: Context) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    private var mItems: List<Order> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun updateData(orders: List<Order>) {
        mItems = orders.sorted()
        notifyDataSetChanged()
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var orderId = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(it.context, OrderDetailActivity::class.java)
                intent.putExtra("orderId", orderId)
                it.context.startActivity(intent)
            }
        }

        fun bindForecast(order: Order) {
            orderId = order.id ?: 0
            val title = "Заказ №$orderId (${order.dateOfOrderCreation})"
            itemView.tv_order_title.text = title
            itemView.tv_order_title.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            itemView.tv_order_from.text = order.startAddress?.address
            itemView.tv_order_to.text = order.endAddress?.address

            if (order.status == Constants.Status.PERFORMING) {
//                val color = ContextCompat.getColor(itemView.tv_order_title.context, R.color.orderPerfoming)
//                itemView.tv_order_title.setTextColor(color)
                itemView.tv_order_title.setBackgroundResource(R.color.orderPerfoming)
            }
        }
    }
}
