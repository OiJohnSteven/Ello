package com.intellect.ello.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
//import com.intellect.ello.Greeting
import android.widget.TextView
import kotlinx.coroutines.*

//fun greet(): String {
//    return Greeting().greeting()
//}

class MainActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Handler().postDelayed(
//            {
//                startActivity(Intent(this, Login::class.java))
//                finish()
//            },
//            3000
//        )
//    }

        //val scope = MainScope()

        activityScope.launch {
            delay(3000)
            startActivity(Intent(this@MainActivity, Login::class.java))
            finish()
        }


//        val tv: TextView = findViewById(R.id.text_view)
//        tv.text = greet()
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
