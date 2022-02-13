package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

//    creating text view nullable with no assigned value at start. It gets value under oncreate method
// make it var as it is changable and make it private so that it is never accessible by another calss
    private var dateDisplayed: TextView? = null
    private var myAgeInMinutes: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        dateDisplayed = findViewById(R.id.dateDisplay)
        myAgeInMinutes = findViewById(R.id.ageInMinutes)

//        calling the button date picker with a function inside.
// The funcation is created an defined later on and used under here.
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }

    fun clickDatePicker() {
//        Here Calender is a Java function
//        Remember we are using getInstance to get the Calender Java Funtion
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayofmonth ->
                Toast.makeText(this,
                    "Year: $selectedYear, Month: ${selectedMonth+1}, Day: $selectedDayofmonth", Toast.LENGTH_LONG).show()
//      Storing the var date, month etc in correct format as below:
                val selectedDate = "$selectedDayofmonth/${selectedMonth+1}/${selectedYear}"

//            dateDisplayed? using question mark as it is nullable
                dateDisplayed?.setText(selectedDate)

//            SimpleDateFormat can be red from official Android Developers Documentation
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)

                val theDate = simpleDateFormat.parse(selectedDate)
                theDate?.let {
                val selectedDateInMinutes = theDate.time/6000
                val currentDate = simpleDateFormat.parse(simpleDateFormat.format((System.currentTimeMillis())))
                currentDate?.let {
                val currentDateInMinutes = currentDate.time/6000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                myAgeInMinutes?.text = differenceInMinutes.toString()
    }

}

            },
            year,
            month,
            day
        )
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
    datePickerDialog.show()

    }

}