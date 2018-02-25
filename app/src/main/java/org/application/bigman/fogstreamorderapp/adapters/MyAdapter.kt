package org.application.bigman.fogstreamorderapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.application.bigman.fogstreamorderapp.data.model.Order
import org.application.bigman.fogstreamorderapp.R

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 20.02.2018.
 **/
class MyAdapter(private var orders: List<Order>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(orders[position])
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    override fun getItemCount(): Int {
        return orders.size
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
