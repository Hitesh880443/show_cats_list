package com.hitesh.showcatslist.catlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.hitesh.showcatslist.model.Pet
import android.widget.ExpandableListView
import com.hitesh.showcatslist.R


class CatListAdapter constructor(
    private val context: Context,
    private val titleList: ArrayList<String>,
    private val dataList: HashMap<String, ArrayList<Pet>>
) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): Any {
        return this.titleList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val listView = parent as ExpandableListView
        listView.expandGroup(groupPosition)
        val listTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_heading_layout, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.tv_heading)
        listTitleTextView.text = listTitle
        return convertView

    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.dataList[this.titleList[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.dataList[this.titleList[groupPosition]]!![childPosition].name
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val expandedListText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null)
        }
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.tv_pet_name)
        expandedListTextView.text = expandedListText
        return convertView

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }
}