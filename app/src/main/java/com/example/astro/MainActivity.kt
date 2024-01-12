package com.example.astro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var lifepath :ImageButton
    private lateinit var personality:ImageButton
    private lateinit var expression:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifepath = findViewById(R.id.lifepath)
        personality = findViewById(R.id.personality)
        expression = findViewById(R.id.expression)


        lifepath.setOnClickListener {
            startActivity(Intent(this,lifepathChoose::class.java))
        }
        personality.setOnClickListener {
            startActivity(Intent(this,personalitycard::class.java))
        }
        expression.setOnClickListener {
            startActivity(Intent(this,expressioncard::class.java))
        }

    }
}