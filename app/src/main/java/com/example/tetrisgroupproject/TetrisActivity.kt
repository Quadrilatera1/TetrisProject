package com.example.tetrisgroupproject

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.*

class TetrisActivity: Activity() {
    private lateinit var gameView : GameView
    private lateinit var game : Tetris
    private lateinit var detector : GestureDetector
    private lateinit var board : GridLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var width : Int = Resources.getSystem( ).displayMetrics.widthPixels
        var height : Int = Resources.getSystem( ).displayMetrics.heightPixels


        setContentView(R.layout.activity_tetris)


        this.board = findViewById(R.id.board)

        var tv = TextView(this)
        tv.text = "This is a textview"

        board.addView(tv)


        var statusBarId : Int = resources.getIdentifier(
            "status_bar_height", "dimen", "android" )
        var statusBarHeight : Int = resources.getDimensionPixelSize( statusBarId )

        gameView = GameView( this, width, height - statusBarHeight )
        game = gameView.getGame()


        var th : TouchHandler = TouchHandler()
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )

        //var gameTimer : Timer = Timer( )
        //gameTimer.schedule( GameTimerTask( this ), 0, 0L + GameView.DELTA_TIME)

    }
    fun goBack( v : View) {
        // go back
        finish( )
    }
    fun updateView( ) {
        gameView.postInvalidate()
    }
    fun updateModel() {

        if (game.rowIsFull()) {
            game.clearRow()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if( event != null ) {
            detector.onTouchEvent( event )
        }
        return super.onTouchEvent(event)
    }

    inner class TouchHandler : GestureDetector.SimpleOnGestureListener ( ) {

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

    }
}