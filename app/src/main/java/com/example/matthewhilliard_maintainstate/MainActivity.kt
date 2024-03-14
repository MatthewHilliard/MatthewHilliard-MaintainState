package com.example.matthewhilliard_maintainstate

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val images = listOf(R.drawable.burger, R.drawable.wizard, R.drawable.lightbulb, R.drawable.studying)
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.edittext)
        val imageView = findViewById<ImageView>(R.id.imageView)


        button.setOnClickListener(){
            val randomPicture = images[Random.nextInt(images.size)]
            imageView.setImageResource(randomPicture)
            editor.putInt("image", randomPicture)
            editor.apply()
        }

        val savedImage = sharedPreferences.getInt("image", R.drawable.burger)
        imageView.setImageResource(savedImage)

        val savedText = sharedPreferences.getString("text", "Hello World!") ?: ""
        editText.setText(savedText)
    }

    override fun onDestroy() {
        Log.d("On destory called", "On destroy called")
        super.onDestroy()
        editor.putString("text", findViewById<EditText>(R.id.edittext).text.toString())
        editor.apply()
    }
}