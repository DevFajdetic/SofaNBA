package com.example.sofanba.ui.settings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sofanba.R
import com.example.sofanba.utils.Constants


class spinnerAdapter (
    context: Context?,
    textViewResourceId: Int,
) : ArrayAdapter<Any?>(context!!, textViewResourceId) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView: View? = convertView
        val holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = inflater.inflate(R.layout.spinner_item_settings, null)
            holder.text1 = convertView.findViewById(R.id.spinnerText1)
            holder.text2 = convertView.findViewById(R.id.spinnerText2)
            convertView.setTag(R.layout.spinner_item_settings, holder)
        } else {
            holder = convertView.getTag(R.layout.spinner_item_settings) as ViewHolder
        }
        holder.text1!!.text = Constants.DATE_FORMAT.uppercase()
        holder.text2!!.setText(position)
        return convertView!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = convertView
        val holder: ViewHolder2
        if (convertView == null) {
            holder = ViewHolder2()
            convertView = inflater.inflate(R.layout.spinner_item_settings, null)
            holder.text1 = convertView.findViewById(R.id.spinnerText1)
            holder.text2 = convertView.findViewById(R.id.spinnerText2)
            convertView.setTag(R.layout.spinner_item_settings, holder)
        } else {
            holder = convertView.getTag(R.layout.spinner_item_settings) as ViewHolder2
        }
        holder.text1!!.text = Constants.DATE_FORMAT.uppercase()
        holder.text2!!.setText(position)
        return convertView
    }

    internal class ViewHolder {
        var text1: TextView? = null
        var text2: TextView? = null
    }

    internal class ViewHolder2 {
        var text1: TextView? = null
        var text2: TextView? = null
    }

}