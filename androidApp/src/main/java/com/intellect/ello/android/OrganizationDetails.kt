package com.intellect.ello.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrganizationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization_details)

        val orContact = intent.getStringExtra("orContact")
        val orName = intent.getStringExtra("orName")
        val orDes = intent.getStringExtra("orDes")
        val orEmail = intent.getStringExtra("orEmail")
        val orLocation = intent.getStringExtra("orLocation")
        val orStatus = intent.getStringExtra("orStatus")

        val contact = findViewById<TextView>(R.id.orContact)
        val name = findViewById<TextView>(R.id.ormName)
        val desc = findViewById<TextView>(R.id.ormDesc)
        val email = findViewById<TextView>(R.id.orEmail)
        val location = findViewById<TextView>(R.id.orLocation)
        val status = findViewById<TextView>(R.id.orStatus)

        contact.text = orContact
        name.text = orName
        desc.text = orDes
        email.text = orEmail
        location.text = orLocation
        status.text = orStatus
    }
}