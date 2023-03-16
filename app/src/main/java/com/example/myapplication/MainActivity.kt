package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var foodSpinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var buttonResult: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Welcome to my App",Toast.LENGTH_LONG).show()

        foodSpinner = findViewById(R.id.food_spinner)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.text_view)
        buttonResult = findViewById(R.id.add_list)
        var editText: EditText = findViewById(R.id.edit_text)
        var resultList:Button=findViewById(R.id.result)



        val foodItems = arrayOf("")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodItems)
        foodSpinner.onItemSelectedListener.apply {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            foodSpinner.adapter = adapter
        }



        button.setOnClickListener {
            val selectedFood = foodSpinner.selectedItem.toString()
            resultList.text = "Your favorite food is $selectedFood"
        }



        foodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedFood = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "You selected $selectedFood", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        buttonResult.setOnClickListener {
            val newFood = editText.text.toString()
            if (newFood.isNotBlank()) {
                val foodList = foodItems.toMutableList()
                foodList.add(newFood)
                val newAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodList)
                newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                foodSpinner.adapter = newAdapter
                editText.text.clear()
                Toast.makeText(this, "Added $newFood to the list", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Please enter a food item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
