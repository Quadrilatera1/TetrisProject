package com.example.tetrisgroupproject

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
    private var delta = 1500
    private lateinit var gameTimer: Timer
    private lateinit var leftButton:Button
    private lateinit var rightButton:Button
    private lateinit var downButton:Button
    private lateinit var rotateButton:Button
    private lateinit var scoreBox:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var width : Int = Resources.getSystem( ).displayMetrics.widthPixels
        var height : Int = Resources.getSystem( ).displayMetrics.heightPixels

        setContentView(R.layout.activity_tetris)

        this.board = findViewById(R.id.board)
        this.scoreBox = findViewById(R.id.scoreBox)

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

        gameView = GameView( this, width, height - statusBarHeight, boxes, this.scoreBox)
        game = gameView.getGame()

        game.boxes = this.boxes
        game.setGameActivity(this)

        leftButton = findViewById<Button>(R.id.left)
        rightButton = findViewById<Button>(R.id.right)
        downButton = findViewById<Button>(R.id.down)
        rotateButton = findViewById<Button>(R.id.rotate)

        leftButton.setOnClickListener {
            goLeft()
        }

        rightButton.setOnClickListener {
            goRight()
        }

        rotateButton.setOnClickListener {
            goRotate()
        }

        downButton.setOnClickListener {
            goDown()
        }



        var th : TouchHandler = TouchHandler()
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )


        game.spawnShape()

        this.gameTimer = Timer( )
        gameTimer.schedule( GameTimerTask( this ), 0, 0L + this.delta)

    }
    fun goBack( v : View) {
        // go back
        finish( )
    }
    fun updateView() {
        gameView.updateGrid()
    }


    fun updateModel() {


        if (game.validSpawn() == false) {
            Log.i("UpdateModel", "No spawn position available: game should end")
        }

        updateSpeed()

        if (game.blockShouldStop()) {
            if (game.placementValid()) {
                game.currShape.falling = false
                game.setBlockRestingPlace()
                game.score += 10
                game.spawnShape()
            } else {
                Log.i("UpdateModel", "Bad placement: game should end")
            }
        }

        for (i in 0..game.boolGrid.size-1) {
            if (game.rowIsFull(i)) {
                game.clearRow(i)
                game.score += 50
                game.moveAboveRowsDown(i)
            }
        }

        if (game.pastTop()) {
            Log.i("updateModel", "The game should end")
        }

        game.moveShapeDown()
    }

    fun updateSpeed() {
        if (game.score >= 500 && game.score < 1000 && this.delta == 1500) {

            this.delta = 1200
            this.gameTimer.cancel()
            this.gameTimer.purge()
            this.gameTimer = Timer()
            this.gameTimer.schedule(GameTimerTask(this), 0, 0L + this.delta)

        }
        if (game.score >= 1000 && game.score < 1500 && this.delta == 1200) {

            this.delta = 900
            this.gameTimer.cancel()
            this.gameTimer.purge()
            this.gameTimer = Timer()
            this.gameTimer.schedule(GameTimerTask(this), 0, 0L + this.delta)

        }
        if (game.score >= 1500 && game.score < 2000 && this.delta == 900) {
            this.delta = 500
            this.gameTimer.cancel()
            this.gameTimer.purge()
            this.gameTimer = Timer()
            this.gameTimer.schedule(GameTimerTask(this), 0, 0L + this.delta)

        }
        if (game.score >= 2000 && game.score < 2500 && this.delta == 500) {
            this.delta = 200
            this.gameTimer.cancel()
            this.gameTimer.purge()
            this.gameTimer = Timer()
            this.gameTimer.schedule(GameTimerTask(this), 0, 0L + this.delta)

        }

        if (game.score >= 2500 && this.delta == 200) {
            this.delta = 50
            this.gameTimer.cancel()
            this.gameTimer.purge()
            this.gameTimer = Timer()
            this.gameTimer.schedule(GameTimerTask(this), 0, 0L + this.delta)

        }
    }

    fun goLeft() {
        game.moveShapeLeft()
        gameView.updateGrid()
    }
    fun goRight() {
        game.moveShapeRight()
        gameView.updateGrid()
    }

    fun goRotate() {
        game.rotateShape()
        gameView.updateGrid()
    }

    fun goDown() {
        if (game.blockShouldStop() == false) {
            game.moveShapeDown()
            gameView.updateGrid()
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

        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
        }

    }
}