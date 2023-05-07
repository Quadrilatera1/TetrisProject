package com.example.tetrisgroupproject

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
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
    private lateinit var boxes:Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var width : Int = Resources.getSystem( ).displayMetrics.widthPixels
        var height : Int = Resources.getSystem( ).displayMetrics.heightPixels

        setContentView(R.layout.activity_tetris)

        this.board = findViewById(R.id.board)

        this.boxes = Array(24, {i->Array<Button>(11, {j->Button(this)})})
        for (i in 0..boxes.size-1) {
            for (j in 0..boxes[i].size-1) {
                boxes[i][j].background = ColorDrawable(Color.WHITE)
                board.addView(boxes[i][j], 57, 57)
            }
        }

        var statusBarId : Int = resources.getIdentifier(
            "status_bar_height", "dimen", "android" )
        var statusBarHeight : Int = resources.getDimensionPixelSize( statusBarId )

        gameView = GameView( this, width, height - statusBarHeight, boxes)
        game = gameView.getGame()

        game.boxes = this.boxes
        game.setGameActivity(this)


        var th : TouchHandler = TouchHandler()
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )

        game.spawnShape()


        var gameTimer : Timer = Timer( )
        gameTimer.schedule( GameTimerTask( this ), 0, 0L + 1500)

    }
    fun goBack( v : View) {
        // go back
        finish( )
    }
    fun updateView() {

        gameView.updateGrid()

    }


    fun updateModel() {

        if (game.currShape == null) {
            game.spawnShape()
        }

        game.moveShapeDown()

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