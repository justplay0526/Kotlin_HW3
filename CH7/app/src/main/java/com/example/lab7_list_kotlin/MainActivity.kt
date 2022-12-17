package com.example.lab7_list_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.lab7_list_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transItem = ArrayList<Item>()
        val counts = ArrayList<String>()
        val priceRange = IntRange(100000,1000000)
        val transPhotoIdArray =
            resources.obtainTypedArray(R.array.trans_image_list)

        for (i in 0 until transPhotoIdArray.length()) {
            val name = "交通工具${i+1}"
            val photo = transPhotoIdArray.getResourceId(i, 0)
            val price = priceRange.random()
            counts.add("${i + 1}個") //新增可購買數量資訊
            transItem.add(Item(photo, name, price))
        }

        transPhotoIdArray.recycle()
        binding.spinner.adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, counts)

        binding.listView.adapter = MyAdapter(
            this, transItem, R.layout.trans_list)

        binding.gridView.numColumns = 2
        binding.gridView.adapter = MyAdapter(
            this, transItem, R.layout.cubee_list)
    }
}

data class Item(
    val photo :Int,
    val name: String,
    val price: Int
)