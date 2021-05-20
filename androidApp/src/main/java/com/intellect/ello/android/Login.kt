package com.intellect.ello.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val x = findViewById<MaterialButton>(R.id.next)
        x.setOnClickListener {
            startActivity(Intent(this, Verification::class.java))
        }
    }
}