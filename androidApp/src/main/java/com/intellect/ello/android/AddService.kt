package com.intellect.ello.android

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.intellect.ello.network.AddServiceAPI
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AddService : AppCompatActivity() {

    val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_service)

        val pro = findViewById<ProgressBar>(R.id.progress)
        pro.visibility = View.INVISIBLE

        val id = findViewById<EditText>(R.id.service)
        val name = id.text.toString()

        val nxt = findViewById<MaterialButton>(R.id.next)
        nxt.setOnClickListener {
       if (name.isEmpty()){
        scope.launch {
            pro.visibility = View.VISIBLE
            val x = AddServiceAPI()
            val result = x.addService(name)

            if (result.status == true){
                pro.visibility = View.INVISIBLE

                AlertDialog.Builder(this@AddService).setTitle(R.string.app_name).setMessage(R.string.rec).setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@AddService, Services::class.java))
                        finish()
                    }

                }).show()
            }
            else if(result.status == false){
                pro.visibility = View.INVISIBLE

                AlertDialog.Builder(this@AddService).setTitle(R.string.app_name).setMessage(R.string.exist).setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@AddService, Services::class.java))
                        finish()
                    }

                }).show()
            }
            else{
                pro.visibility = View.INVISIBLE

                AlertDialog.Builder(this@AddService).setTitle(R.string.app_name).setMessage(R.string.fail).setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }

                }).show()

            }


            } }
            else{
                AlertDialog.Builder(this@AddService).setTitle(R.string.app_name).setMessage(R.string.miss).setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()

                    }

                }).show()
            }
        }
    }
}