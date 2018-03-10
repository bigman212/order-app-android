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
    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */

    private var mOrdersList: List<Order> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, viewGroup, false)
        val orderViewHolder = ViewHolder(view)
        orderViewHolder.itemView.setOnClickListener {
            val intent = Intent(mContext, OrderDetailActivity::class.java)
            mContext.startActivity(intent)
        }

        return orderViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(mOrdersList[position])
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    override fun getItemCount(): Int {
        return mOrdersList.size
    }

    fun updateData(orders: List<Order>) {
        mOrdersList = orders
        notifyDataSetChanged()
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindForecast(order: Order) {
            itemView.tv_order_date.text = order.date.toString()
            itemView.tv_order_from.text = order.from
            itemView.tv_order_to.text = order.to
        }
    }
}
