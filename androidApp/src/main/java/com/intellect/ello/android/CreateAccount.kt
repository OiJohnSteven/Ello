package com.intellect.ello.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val x = findViewById<MaterialButton>(R.id.create)
        x.setOnClickListener {
            startActivity(Intent(this, Services::class.java))
        }
    }
}