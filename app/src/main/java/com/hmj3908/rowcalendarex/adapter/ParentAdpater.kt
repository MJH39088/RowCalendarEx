package com.hmj3908.rowcalendarex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hmj3908.rowcalendarex.R
import com.hmj3908.rowcalendarex.databinding.ItemSectionBinding
import com.hmj3908.rowcalendarex.utils.SectionItem

class ParentAdapter :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>(), ChildAdapter.Delegate {

    private val items: ArrayList<SectionItem> = arrayListOf()
    private val childAdapter = ChildAdapter(this)

    fun addSectionItem(sectionItem: SectionItem) {
        items.add(sectionItem)
        notifyItemChanged(items.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding).apply {
            with(binding.root) {
                setOnClickListener {
                    val position =
                        adapterPosition.takeIf { position -> position != RecyclerView.NO_POSITION }
                            ?: return@setOnClickListener
                    val item = items[position]
                    item.expanded = !item.expanded
                    notifyItemChanged(position)
                }
                parentLayoutResource = R.layout.item_section_parent
                secondLayoutResource = R.layout.item_section_child
                duration = 200L
                secondLayout.findViewById<RecyclerView>(R.id.recyclerViewChild).adapter = childAdapter
            }
        }
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val sectionItem = items[position]
        with(holder.binding.expandableLayout) {
            if (sectionItem.expanded) {
                expand()
            } else {
                collapse()
            }
            parentLayout.findViewById<TextView>(R.id.title).text = sectionItem.title
            parentLayout.setBackgroundColor(ContextCompat.getColor(context, sectionItem.color))
            childAdapter.addItemList(sectionItem.itemList)
        }
    }

    override fun onRowItemClick(position: Int, title: String, context: Context) =
        Toast.makeText(context, "position : $position, title: $title", Toast.LENGTH_SHORT).show()

    override fun getItemCount() = items.size

    class ParentViewHolder(val binding: ItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}