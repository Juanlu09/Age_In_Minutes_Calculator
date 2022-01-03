package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDate: TextView? = null
    private var tvMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDateSelect: Button = findViewById(R.id.btnDateSelect)
        tvDate = findViewById(R.id.tv_Date)
        tvMinutes = findViewById(R.id.tv_Minutes)

        btnDateSelect.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, getString(R.string.selection_done), Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {

                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {

                        val currentDayInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDayInMinutes - selectedDateInMinutes

                        tvMinutes?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}