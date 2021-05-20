package com.intellect.ello.android

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.intellect.ello.network.ServiceAPI
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class Services : AppCompatActivity() {
    val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        scope.launch {
            val list = ServiceAPI()

            if (list.getService().size ==0 ){
                AlertDialog.Builder(this@Services).setTitle(R.string.app_name).setMessage(R.string.empty).setPositiveButton(R.string.ok, object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }

                })
            }
            else {
                val recycler = findViewById<RecyclerView>(R.id.serviceRecycler)
                recycler.adapter = ServicesAdapter(list.getService(), this@Services)
                recycler.layoutManager = LinearLayoutManager(this@Services)

            }

            val pro = findViewById<ProgressBar>(R.id.progress)
            pro.visibility = View.INVISIBLE

        }


        val ser = findViewById<ImageView>(R.id.serSearc)
        ser.setOnClickListener {

            val recycler = findViewById<RecyclerView>(R.id.serviceRecycler)
            recycler.visibility = View.INVISIBLE
            val pro = findViewById<ProgressBar>(R.id.progress)
            pro.visibility = View.VISIBLE
            scope.launch {

                val txt = findViewById<EditText>(R.id.editText)

                val list = ServiceAPI()
                recycler.visibility = View.VISIBLE
                pro.visibility = View.INVISIBLE
                recycler.adapter = ServicesAdapter(list.searchService(txt.text.toString()), this@Services)
                recycler.layoutManager = LinearLayoutManager(this@Services)
            }

        }

        val addS = findViewById<ImageView>(R.id.addS)
        addS.setOnClickListener {
            val intent = Intent(this, AddService::class.java)
            startActivity(intent)
        }


    }

    override fun onPause() {
        super.onPause()
        scope.cancel()
    }
}