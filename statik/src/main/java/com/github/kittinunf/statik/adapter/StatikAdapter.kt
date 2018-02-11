package com.github.kittinunf.statik.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.representable.ItemRepresentable
import com.github.kittinunf.statik.viewholder.BindableViewHolder
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import kotlin.properties.Delegates

open class StatikAdapter(private val typeFactory: TypeFactory = defaultTypeFactory) : RecyclerView.Adapter<StatikViewHolder>() {

    var sections by Delegates.observable(emptyList<Section>()) { _, _, value ->
        //calculate items
        items = value.flatMap(::createRepresentable)
        notifyDataSetChanged()
    }

    private var items = emptyList<ItemRepresentable>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StatikViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        val viewHolder = typeFactory.viewHolder(viewType, view)
        return viewHolder
    }

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(viewHolder: StatikViewHolder, position: Int) {
        (viewHolder as BindableViewHolder<ItemRepresentable>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].type(typeFactory)

    override fun getItemCount(): Int = items.size

    private fun createRepresentable(section: Section): List<ItemRepresentable> {
        val items = mutableListOf<ItemRepresentable>()

        if (section.header != null) {
            items.add(section.header)
        }

        items.addAll(section.rows)

        if (section.footer != null) {
            items.add(section.footer)
        }
        return items
    }

    override fun onViewRecycled(holder: StatikViewHolder?) {
        super.onViewRecycled(holder)
        holder?.onViewRecycled()
    }
}