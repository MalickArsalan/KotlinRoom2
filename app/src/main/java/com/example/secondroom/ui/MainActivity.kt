package com.example.secondroom.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.secondroom.R
import com.example.secondroom.db.User
import androidx.activity.viewModels
import com.example.secondroom.UsersApplication
import com.example.secondroom.ui.main.viewmodel.UserViewModel
import com.example.secondroom.ui.main.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private val wordViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UsersApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items= resources.getStringArray(R.array.genderArray)
        val  spinner : Spinner =  findViewById(R.id.spinnerGender);
        val  buttonView : Button =  findViewById(R.id.buttonView);
        val  buttonSubmit : Button =  findViewById(R.id.buttonSubmit);
        val  firstNameET :EditText = findViewById(R.id.firstNameET);
        val  middleNameET :EditText = findViewById(R.id.lastNameET);
        val  lastNameET :EditText = findViewById(R.id.middleNameET);
        val  fatherNameET :EditText = findViewById(R.id.fatherNameET);


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

        buttonSubmit.setOnClickListener{
            val firstName = firstNameET.text.toString()
            val middleName = middleNameET.text.toString()
            val lastName = lastNameET.text.toString()
            val fatherName = fatherNameET.text.toString()
            val gender = if (spinner.selectedItem.toString() == "Male") "M" else "F"

            if (firstName.isEmpty()){
                firstNameET.error = "First Name Required"
                firstNameET.requestFocus()
                return@setOnClickListener
            }

            if (middleName.isEmpty()){
                middleNameET.error = "Middle Name Required"
                middleNameET.requestFocus()
                return@setOnClickListener
            }
            if (lastName.isEmpty()){
                lastNameET.error = "Last Name Required"
                lastNameET.requestFocus()
                return@setOnClickListener
            }

            if (fatherName.isEmpty()){
                fatherNameET.error = "Father Name Required"
                fatherNameET.requestFocus()
                return@setOnClickListener
            }

            val user = User(firstName,middleName,lastName,fatherName,gender)

            wordViewModel.insert(user)

        }
    }
}