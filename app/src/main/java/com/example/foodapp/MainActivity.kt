package com.example.foodapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var foodListView: ListView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var totalTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var placeOrderButton: Button

    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodListView = findViewById(R.id.foodListView)
        totalTextView = findViewById(R.id.totalTextView)
        checkoutButton = findViewById(R.id.checkoutButton)
        placeOrderButton = findViewById(R.id.placeOrderButton)

        foodAdapter = FoodAdapter(this, generateFoodList()) { price ->
            totalPrice += price
            updateTotal()
        }
        foodListView.adapter = foodAdapter

        checkoutButton.setOnClickListener {
            // Show the place order button and hide the checkout button
            checkoutButton.visibility = Button.GONE
            placeOrderButton.visibility = Button.VISIBLE
        }

        placeOrderButton.setOnClickListener {
            // Handle placing the order
            // For simplicity, let's just display a toast message
            Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show()
            // You can add your order placement logic here
        }
    }

    private fun generateFoodList(): ArrayList<FoodItem> {
        val foodList = ArrayList<FoodItem>()
        // Add sample food items
        foodList.add(FoodItem("Burger", "Delicious burger with juicy patty", 5.99, R.drawable.burger))
        foodList.add(FoodItem("Pizza", "Cheesy pizza with your favorite toppings", 8.99, R.drawable.pizza))
        foodList.add(FoodItem("Salad", "Healthy salad with fresh veggies", 4.99, R.drawable.salad))
        return foodList
    }

    private fun updateTotal() {
        totalTextView.text = getString(R.string.total_format, totalPrice)
    }
}
