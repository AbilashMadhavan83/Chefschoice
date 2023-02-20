package com.example.chefschoice.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.chefschoice.R
import com.example.chefschoice.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //findViewById<TextView>(R.id.text).typeface = ResourcesCompat.getFont(this, R.font.gochi_hand)

//        val backgroundImg : ImageView = findViewById(R.id.imageView)
//        backgroundImg.animate().rotation(360f).setDuration(3000).start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },1000)


    }
}