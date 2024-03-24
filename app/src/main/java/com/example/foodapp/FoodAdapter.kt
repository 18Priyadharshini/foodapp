package com.example.foodapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class FoodAdapter(
    private val context: Context,
    private val foodList: ArrayList<FoodItem>,
    private val onItemAddedToCart: (Double) -> Unit // Callback to notify when item is added to cart
) : ArrayAdapter<FoodItem>(context, 0, foodList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_food, parent, false)
        }

        val currentItem = foodList[position]

        val foodImageView: ImageView = itemView!!.findViewById(R.id.foodImageView)
        val foodNameTextView: TextView = itemView.findViewById(R.id.foodNameTextView)
        val foodDescriptionTextView: TextView = itemView.findViewById(R.id.foodDescriptionTextView)
        val foodPriceTextView: TextView = itemView.findViewById(R.id.foodPriceTextView)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)

        foodImageView.setImageResource(currentItem.imageResId)
        foodNameTextView.text = currentItem.name
        foodDescriptionTextView.text = currentItem.description
        foodPriceTextView.text = context.getString(R.string.price_format, currentItem.price)

        addToCartButton.setOnClickListener {
            // Calculate the total price and notify the listener
            onItemAddedToCart(currentItem.price.toDouble())
            // Handle add to cart button click here
            // For simplicity, let's just display a toast message
            Toast.makeText(context, "Added ${currentItem.name} to cart", Toast.LENGTH_SHORT).show()
        }

        return itemView
    }
}
