package com.example.astro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.database.*

class Result : AppCompatActivity() {

    private lateinit  var number : String
    private lateinit var category : String
    private lateinit var list : MutableList<String>

    private lateinit var numberText : TextView
    private lateinit var descriptionText:TextView
    private lateinit var card:CardView
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        number = intent.getStringExtra("number").toString()
        category = intent.getStringExtra("category").toString()
        list = mutableListOf()
        databaseReference = FirebaseDatabase.getInstance().reference.child(category).child(number)

        numberText = findViewById(R.id.number)
        descriptionText = findViewById(R.id.description)
        card = findViewById(R.id.resultcard)

        fetchData()
    }

    private fun fetchData() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val description = dataSnapshot.child("description").getValue(String::class.java)
                    val traits = dataSnapshot.child("traits")
                    for(i in traits.children){
                        val dataValue = i.getValue(String::class.java)
                        Log.d("datavalue",dataValue.toString())
                        list.add(dataValue.toString())

                    }
                    Log.d("desc",description.toString())

                    var data ="Description\n\n" +  description.toString()
                    if(list.isNotEmpty()){
                        data+="\n\nTraits\n\n"
                        var cnt = 1
                        for(i in list){
                            data+="${cnt}. " + i + "\n"
                            cnt++
                        }
                    }

                    numberText.text = category +": "+ number
                    descriptionText.text = data
                    numberText.visibility = View.VISIBLE
                    card.visibility = View.VISIBLE
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}