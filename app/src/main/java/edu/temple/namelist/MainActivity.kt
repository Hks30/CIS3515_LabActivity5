package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String> // Change to MutableList
    lateinit var customAdapter: CustomAdapter // Store a reference to the adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        // Initialize and set the custom adapter
        customAdapter = CustomAdapter(names, this)
        spinner.adapter = customAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // Check if the position is valid before accessing it
                if (p2 < names.size) {
                    nameTextView.text = names[p2] // Use names instead of getItemAtPosition
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        //2nd bug
        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            if (selectedPosition >= 0 && selectedPosition < names.size) {
                names.removeAt(selectedPosition)
                customAdapter.notifyDataSetChanged() // adapter to the data change

                if (names.isNotEmpty()) {
                    // reset selection to the first namr
                    spinner.setSelection(0)
                    nameTextView.text = names[0]
                } else {
                    // if no names are left
                    spinner.setSelection(-1)
                    nameTextView.text = ""
                    Toast.makeText(this, "No names left", Toast.LENGTH_SHORT).show() // Inform the user
                }
            }
        }

    }
}
