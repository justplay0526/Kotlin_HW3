package com.example.lab7_list_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.lab7_list_kotlin.databinding.CubeeListBinding
import com.example.lab7_list_kotlin.databinding.TransListBinding


class MyAdapter(
    context: Context,
    data: ArrayList<Item>,
    private var layout: Int,
) : ArrayAdapter<Item>(context, layout, data) {
    private lateinit var bindingcubee: CubeeListBinding
    private lateinit var bindingtrans: TransListBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        bindingcubee = CubeeListBinding.inflate(LayoutInflater.from(context))
        bindingtrans = TransListBinding.inflate(LayoutInflater.from(context))

        val view = View.inflate(parent.context, layout, null)
        val item = getItem(position) ?: return view

        bindingcubee.cubeeImageView.setImageResource(item.photo)
        bindingtrans.transImageView.setImageResource(item.photo)

        bindingcubee.cubeeName.text = item.name
        bindingtrans.transName.text =  "${item.name}: ${item.price}元"


        return if (layout == R.layout.cubee_list)
            bindingcubee.root
        else
            bindingtrans.root

    }
}