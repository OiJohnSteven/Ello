package com.intellect.ello.android

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intellect.ello.network.OrganizationAPI
import kotlinx.coroutines.*


class Organization : AppCompatActivity() {
    val scope = MainScope()
    val sScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization)

        val sid = intent.getIntExtra("id", 1)
        scope.launch {
            val list = OrganizationAPI()
            val recycler = findViewById<RecyclerView>(R.id.organizationRecycler)

            if (list.getOrganizations(sid).size == 0 ){

                Log.d("id", "${list.getOrganizations(sid)}")
                recycler.visibility = View.INVISIBLE
                AlertDialog.Builder(this@Organization).setTitle(R.string.app_name).setMessage(R.string.emptyO).setPositiveButton(R.string.ok, object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }

                })
            }
            else{

                Log.d("did", "${list.getOrganizations(sid)}")

                recycler.adapter = OrganizationAdapter(list.getOrganizations(sid), this@Organization)
                recycler.layoutManager = LinearLayoutManager(this@Organization)
            }


            val pro = findViewById<ProgressBar>(R.id.progress)
            pro.visibility = View.INVISIBLE

        }

        val or = findViewById<ImageView>(R.id.orSearc)
        or.setOnClickListener {

            val recycler = findViewById<RecyclerView>(R.id.organizationRecycler)
            recycler.visibility = View.INVISIBLE
            val pro = findViewById<ProgressBar>(R.id.progress)
            pro.visibility = View.VISIBLE
            sScope.launch {

                val txt = findViewById<EditText>(R.id.edit)

                val list = OrganizationAPI()
                recycler.visibility = View.VISIBLE
                pro.visibility = View.INVISIBLE
                Log.d("searc", "${list.searchOrganization(txt.text.toString())}")
                recycler.adapter = OrganizationAdapter(list.searchOrganization(txt.text.toString()), this@Organization)
                recycler.layoutManager = LinearLayoutManager(this@Organization)
            }

        }

        val addO = findViewById<ImageView>(R.id.addO)
        addO.setOnClickListener {
            val intent = Intent(this, AddOrganization::class.java)
            startActivity(intent)
        }


    }


    override fun onPause() {
        super.onPause()
        scope.cancel()
    }
}