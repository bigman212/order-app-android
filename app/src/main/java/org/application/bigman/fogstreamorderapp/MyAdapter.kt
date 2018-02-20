package org.application.bigman.fogstreamorderapp

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.application.bigman.fogstreamorderapp.R.id.tv_order_name

/**
 * org.application.bigman.fogstreamorderapp
 * Created by bigman212 on 20.02.2018.
 **/
class MyAdapter(private var records: List<Order>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.bindForecast(record)
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    override fun getItemCount(): Int {
        return records.size
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindForecast(order: Order) {
            itemView.tv_order_name.text = order.name
            itemView.tv_order_number.text = order.number.toString()
        }
    }
}
