package com.example.tetrisgroupproject

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun playTetris( v : View) {
        // go to DataActivity
        var myIntent : Intent = Intent( this, TetrisActivity::class.java )
        startActivity( myIntent )
    }

}