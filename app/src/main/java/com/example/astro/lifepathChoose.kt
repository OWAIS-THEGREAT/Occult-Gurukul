package com.example.astro

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.astro.Result
import java.util.*

class lifepathChoose : AppCompatActivity() {
    private lateinit var choose:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifepath_choose)

        choose = findViewById(R.id.choose)

        choose.setOnClickListener {
            showDatePickerDialog()
        }

    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                val lifePathNumber = calculateLifePathNumber(selectedDate)
                Log.d("number",lifePathNumber.toString())
                val intent = Intent(this@lifepathChoose,Result::class.java)
                intent.putExtra("category","LifePathNumbers")
                intent.putExtra("number",lifePathNumber.toString())
                startActivity(intent)
            },
            year,
            month,
            day
        )


        datePickerDialog.show()
    }

    private fun calculateLifePathNumber(dateOfBirth: String): Int {
        val parts = dateOfBirth.split("-")
        if (parts.size != 3) {
            throw IllegalArgumentException("Invalid date format")
        }

        val year = parts[0].toInt()
        val month = parts[1].toInt()
        val day = parts[2].toInt()

        var lifePathNumber = calculateSingleDigit(year) +
                calculateSingleDigit(month) +
                calculateSingleDigit(day)

        if (lifePathNumber == 11 || lifePathNumber == 22 || lifePathNumber == 33) {
            return lifePathNumber
        }

        while (lifePathNumber > 9) {
            lifePathNumber = calculateSingleDigit(lifePathNumber)
        }

        return lifePathNumber
    }

    private fun calculateSingleDigit(number: Int): Int {
        var result = 0
        var n = number

        while (n > 0) {
            result += n % 10
            n /= 10
        }

        return result
    }
}