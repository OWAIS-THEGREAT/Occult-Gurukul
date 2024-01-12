package com.example.astro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signin : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var newacc:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)


        auth = Firebase.auth
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)


        val login  = findViewById<Button>(R.id.login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        newacc = findViewById(R.id.newacc)

        newacc.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
            finish()
        }

        login.setOnClickListener {

            if(checking()){
                val user = email.text.toString()
                val password = password.text.toString()
                auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(this){
                        task->
                    if(task.isSuccessful){

                        val editor = sharedPreferences.edit()
                        editor.putString("named", "owais")
                        editor.apply()


                        Handler(Looper.getMainLooper()).postDelayed({
                            val savedName = sharedPreferences.getString("named", "")
                            Log.d("SavedName", savedName.toString())

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }, 1000)

                    }
                    else{
                        Toast.makeText(this,"wrong detail", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"enter the detail", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checking():Boolean{
        if(email.text.trim { it <=' ' }.isNotEmpty() && password.text.trim { it<=' ' }.isNotEmpty()){
            return true
        }
        return false
    }
}