package com.example.astro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class expressioncard : AppCompatActivity() {
    private lateinit var submit : Button
    private lateinit var enterName : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expressioncard)

        enterName =findViewById(R.id.enterName)
        submit = findViewById(R.id.submitex)
        val inputname = enterName.text

        submit.setOnClickListener {
            val number = calculateExpressionNumber(inputname.toString())
            Log.d("res",number.toString())


            val intent = Intent(this@expressioncard,Result::class.java)
            intent.putExtra("category","ExpressionNumber")
            intent.putExtra("number",number.toString())
            startActivity(intent)
        }

    }

    fun calculateExpressionNumber(name: String): Int {

        val letterValues = mapOf(
            'a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6,
            'g' to 7, 'h' to 8, 'i' to 9, 'j' to 1, 'k' to 2, 'l' to 3,
            'm' to 4, 'n' to 5, 'o' to 6, 'p' to 7, 'q' to 8, 'r' to 9,
            's' to 1, 't' to 2, 'u' to 3, 'v' to 4, 'w' to 5, 'x' to 6,
            'y' to 7, 'z' to 8
        )


        val lowercaseName = name.toLowerCase()

        val expressionNumber = lowercaseName
            .filter { it.isLetter() && it in letterValues }
            .sumBy { letterValues[it]!! }

        return reduceToSingleDigit(expressionNumber)
    }

    fun reduceToSingleDigit(number: Int): Int {
        return if (number > 9) {
            reduceToSingleDigit(number.toString().sumBy { it.toString().toInt() })
        } else {
            number
        }
    }

}