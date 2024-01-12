package com.example.astro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var register : Button
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var already : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        register = findViewById(R.id.register)
        email = findViewById(R.id.emailreg)
        password  = findViewById(R.id.Password1)
        already = findViewById(R.id.already)
        already.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
            finish()
        }
        register.setOnClickListener {
            preformRegiste()
        }
    }

    private fun preformRegiste() {
        val inputemail = email.text.toString()
        val inputpassword = password.text.toString()


//        Toast.makeText(this,inputname,Toast.LENGTH_LONG).show()


        if (inputemail.isBlank() || inputpassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(inputemail, inputpassword)
            .addOnCompleteListener {task->
                if(task.isSuccessful){

                    val intent = Intent(this,Signin::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}