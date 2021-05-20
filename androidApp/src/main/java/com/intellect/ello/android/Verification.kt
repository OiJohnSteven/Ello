package com.intellect.ello.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val x = findViewById<MaterialButton>(R.id.verify)
        x.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
    }
}