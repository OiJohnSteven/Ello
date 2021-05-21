package com.intellect.ello.android

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.intellect.ello.network.AddOrganizationAPI
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URLEncoder


class AddOrganization : AppCompatActivity() {
    var image_uri: Uri? = null
    val PICK_PERMISSION_CODE = 1002
    val IMAGE_PICK_CODE = 1003
    private lateinit var im:ImageView

    val scope = MainScope()
    var ConvertImage:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_organization)

        val create = findViewById<MaterialButton>(R.id.create)
        val name = findViewById<TextInputEditText>(R.id.orName)
        val desc = findViewById<TextInputEditText>(R.id.orDesc)
        val contact = findViewById<TextInputEditText>(R.id.orContact)
        val email = findViewById<TextInputEditText>(R.id.orEmail)
        val location = findViewById<TextInputEditText>(R.id.orLocation)
        im = findViewById<ImageView>(R.id.imageView2)

        create.setOnClickListener{
            val gend = arrayOf<String?>("Registered", "UnRegistered")

        val adapter = ArrayAdapter<Any?>(this, R.layout.list_items, gend)

        val editTextFilledExposedDropdown: AutoCompleteTextView = findViewById(R.id.orStatus)
        editTextFilledExposedDropdown.setAdapter(adapter)

        editTextFilledExposedDropdown.setOnClickListener {
            (it as AutoCompleteTextView).showDropDown()
        }

        val orName = name.text.toString()
        val orDesc = desc.text.toString()
        val orContact = contact.text.toString()
        val orEmail= email.text.toString()
        val orLocation= location.text.toString()
        val orStatus = editTextFilledExposedDropdown.text.toString()

        //val orImage = File(getImagePath(image_uri)!!)

           // val image = ImageIO.read(File(""))

//        if (orName.isEmpty() || orDesc.isEmpty() || orContact.isEmpty() || orEmail.isEmpty() || orLocation.isEmpty() || orStatus.isEmpty() || image_uri == null) {
//
//            Toast.makeText(this, "Enter all details", Toast.LENGTH_LONG).show()
//            return@setOnClickListener
//        }
            scope.launch {
                val response = AddOrganizationAPI()

                Log.d("result", "${response.upload(URLEncoder.encode(getImagePath(image_uri)!!, "UTF-8"),  getImagePath(image_uri)!!)}")

                Toast.makeText(this@AddOrganization, "${response.upload(URLEncoder.encode(getImagePath(image_uri)!!, "UTF-8"),  getImagePath(image_uri)!!)}", Toast.LENGTH_LONG).show()

//                val response = AddOrganizationAPI()
//
//                Log.d("result", "${response.addOrganization(1, orName,orDesc, orContact, orEmail, orLocation, URLEncoder.encode(ConvertImage, "UTF-8"),  orStatus)}")
//
//                Log.d("res", orName + orDesc + orContact + orEmail + orLocation + URLEncoder.encode(ConvertImage, "UTF-8") + orStatus)
//                //Toast.makeText(this@AddOrganization,
                    //"${response.addOrganization(1, orName,orDesc, orContact, orEmail, orLocation, URLEncoder.encode(ConvertImage, "UTF-8"), orStatus)}", Toast.LENGTH_LONG).show()
            }





        }

        im.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PICK_PERMISSION_CODE);
                } else {
                    //permission already granted
                    pickImageFromGallery();
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }
    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions:Array<out String>, grantResults:IntArray) {
        //this method is called, when user presses Allow or Deny from Permission Request Popup
        when (requestCode) {

            PICK_PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image_uri = data!!.data
            //img.setImageURI(image_uri)

            val bitmap = MediaStore.Images.Media
                .getBitmap(
                    contentResolver,
                    image_uri
                )
            im.setImageBitmap(bitmap)


            val byteArrayOutputStreamObject: ByteArrayOutputStream

            byteArrayOutputStreamObject = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject)

            val byteArrayVar = byteArrayOutputStreamObject.toByteArray()

            ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT)

        }
    }

    fun getImagePath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri!!, projection, null, null, null) ?: return null
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s = cursor.getString(columnIndex)
        cursor.close()
        return s
    }



}