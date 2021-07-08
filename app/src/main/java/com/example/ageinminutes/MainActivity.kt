package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnDatePicker)
        button.setOnClickListener {view ->
            Clickdatepicker(view)
            }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun Clickdatepicker(view : View){

        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val dayOfMonth = mycalendar.get(Calendar.DAY_OF_MONTH)

      val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
            view, year, month, dayOfMonth ->

            Toast.makeText(this,"CHOSEN YEAR IS" + year, Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            val mytxt : TextView = findViewById(R.id.TvSelectedDate)
            mytxt.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val thedate = sdf.parse(selectedDate)
            val dateinminutes =thedate!!.time / 60000
            val currentdate =sdf.parse(sdf.format(System.currentTimeMillis()))
            val currdateinminutes = currentdate!!.time / 60000
            val Differnceinminutes = currdateinminutes - selectedDate.toDouble()

            val ageinminutes : TextView = findViewById(R.id.TvSelectedDateinminutes)
            ageinminutes.setText(Differnceinminutes.toString())
        }
                ,year
                ,month
                ,dayOfMonth)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}