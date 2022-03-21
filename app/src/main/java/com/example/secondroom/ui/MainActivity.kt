package com.example.secondroom.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.secondroom.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items= resources.getStringArray(R.array.genderArray)
        val  spinner : Spinner =  findViewById(R.id.spinnerGender);
        val  buttonView : Button =  findViewById(R.id.button1);

        val spinnerAdapter= object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            items
        )
        {
            override fun isEnabled(position: Int): Boolean {
                return position !=0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view:TextView = super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0){
                    view.setTextColor(Color.GRAY)
                }

                return view
            }

        }

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = parent!!.getItemAtPosition(position).toString()
                if(value == items[0]){
                    (view as TextView).setTextColor(Color.GRAY)
                }
            }

        }

        buttonView.setOnClickListener{
            val intent = Intent(this, RoomView::class.java)
            startActivity(intent)
        }

    }
}