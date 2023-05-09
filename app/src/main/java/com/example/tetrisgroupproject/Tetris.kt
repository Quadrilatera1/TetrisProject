package com.example.tetrisgroupproject

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.GridLayout
import java.util.*
import kotlin.math.*
import kotlin.random.*
import android.util.Log

class Tetris {

    var score: Int = 0
    private var highScore: Int = 0
    private var gameOver : Boolean = false
    private lateinit var e : SharedPreferences.Editor
    private lateinit var pref: SharedPreferences
    lateinit var boxes:Array<Array<Button>>
    lateinit var boolGrid:Array<Array<Boolean>>
    lateinit var currShape:Block
    lateinit var activity: TetrisActivity


    val LINES = 22
    val COLUMNS = 12


    constructor(context: Context, boxes:Array<Array<Button>>){

        this.pref = context!!.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE )

        this.e = pref.edit()

        var firstRun = pref.getBoolean("firstRun", true)

        if (firstRun) {
            this.highScore = 0
            this.e.putBoolean("firstRun", false)
            this.e.putInt("highScore", this.highScore)
            this.e.commit()
            this.highScore = 0
        } else {
            this.highScore = this.pref.getInt("highScore", 0)
        }

        this.boxes = boxes

        this.boolGrid = Array(24, {i->Array<Boolean>(11, {j->false})})

    }
    fun setHighScore(newHighScore: Int) : Unit {
        highScore = newHighScore
    }
    fun getHighScore() : Int{
        return this.pref.getInt("highScore", 0)
    }

    fun getFinalScore() :Int  {
        return this.pref.getInt("finalScore", 0)
    }

    fun setGameActivity(activity: TetrisActivity) {
        this.activity = activity
    }

    fun spawnShape() {
        //This will randomly generate a piece eventually
        //For testing purposes, it just makes a single IBlock
        //All shapes' initial coordinates should be (0, 5)
        var random = (0..6).random()

        if (random == 0) {
            this.currShape = IBlock(1, 5)
        } else if (random == 1) {
            this.currShape = JBlock(1,5)
        } else if (random == 2) {
            this.currShape = LBlock(1,5)
        } else if (random == 3) {
            this.currShape = OBlock(1,5)
        } else if (random == 4) {
            this.currShape = SBlock(1,5)
        } else if (random == 5) {
            this.currShape = TBlock(1,5)
        } else if (random == 6) {
            this.currShape = ZBlock(1,5)
        }

    }

    fun validSpawn():Boolean {
        if (boolGrid[currShape.A.x][currShape.A.y] == true
            || boolGrid[currShape.B.x][currShape.B.y] == true
            || boolGrid[currShape.C.x][currShape.C.y] == true
            || boolGrid[currShape.D.x][currShape.D.y] == true) {
            return false
        }
        return true
    }

    fun placementValid():Boolean {
        var A = currShape.A
        var B = currShape.B
        var C = currShape.C
        var D = currShape.D

        if (A.x < 0 || B.x < 0 || C.x < 0 || D.x < 0) {
            return false
        }
        return true

    }

    fun moveShapeDown() {
        this.currShape.moveDown()
    }

    fun canMoveLeft():Boolean {
        var A = currShape.A
        var B = currShape.B
        var C = currShape.C
        var D = currShape.D

        if (currShape.falling == false) {
            return false
        }

        if (A.y-1 < 0 || B.y-1 < 0 || C.y-1 < 0 || D.y-1 < 0) {
            Log.w("canMoveLeft", "False")

            return false
        }
        if (boolGrid[A.x][A.y-1] || boolGrid[B.x][B.y-1] || boolGrid[C.x][C.y-1] || boolGrid[D.x][D.y-1]) {
            Log.w("canMoveLeft", "False")

            return false

        }
        return true
    }

    fun moveShapeLeft() {
        //Calculate a projection of the shape's next movement using its coordinates
        //If the projection is outside the bounds of the grid, or it would
        //move into any blocks marked true then don't move
        //otherwise
        if (this.canMoveLeft()) {
            currShape.moveLeft()
        }
    }

    fun canMoveRight():Boolean {

        var A = currShape.A
        var B = currShape.B
        var C = currShape.C
        var D = currShape.D

        if (currShape.falling == false) {
            return false
        }

        if (A.y+1 > 10 || B.y+1 > 10 || C.y+1 > 10 || D.y+1 > 10) {
            return false
        }
        if (boolGrid[A.x][A.y+1] || boolGrid[B.x][B.y+1] || boolGrid[C.x][C.y+1] || boolGrid[D.x][D.y+1]) {
            return false
        }
        return true

    }

    fun moveShapeRight() {
        //Calculate a projection of the shape's next movement using its coordinates
        //If the projection is outside the bounds of the grid, or it would
        //move into any blocks marked true then don't move
        //otherwise
        if (this.canMoveRight()) {
            currShape.moveRight()
        }
    }

    fun rotateShape() {
        //Calculate a projection of the shape's rotation using its coordinates
        //If the projection is entirely within the grid, then call currShape.rotate()
        if (currShape.canRotate(boolGrid) && currShape.falling) {
            currShape.rotate()
        }
    }

    fun blockShouldStop():Boolean {
        var nextA = currShape.A
        var nextB = currShape.B
        var nextC = currShape.C
        var nextD = currShape.D

        if (nextA.x+1 == 24 || nextB.x+1 == 24 || nextC.x+1 == 24 || nextD.x+1 == 24) {
            return true
        }
        if (boolGrid[nextA.x+1][nextA.y] || boolGrid[nextB.x+1][nextB.y] || boolGrid[nextC.x+1][nextC.y] || boolGrid[nextD.x+1][nextD.y]) {
            return true
        }
        return false

    }

    fun pastTop():Boolean {

        var nextA = currShape.A
        var nextB = currShape.B
        var nextC = currShape.C
        var nextD = currShape.D

        if (nextA.x < 0 || nextB.x < 0 || nextC.x < 0 || nextD.x < 0) {
            return true
        }
        return false

    }

    fun setBlockRestingPlace() {

        var A = currShape.A
        var B = currShape.B
        var C = currShape.C
        var D = currShape.D

        this.boolGrid[A.x][A.y] = true
        this.boolGrid[B.x][B.y] = true
        this.boolGrid[C.x][C.y] = true
        this.boolGrid[D.x][D.y] = true


    }

    fun rowIsFull(rowIndex: Int) : Boolean {
        for (j in 0..this.boolGrid[rowIndex].size-1) {
            if (this.boolGrid[rowIndex][j] == false) {
                return false
            }
        }
        return true
    }

    fun clearRow(rowIndex: Int) {

        for (j in 0..boolGrid[rowIndex].size-1) {
            this.boolGrid[rowIndex][j] = false
        }

    }

    fun moveAboveRowsDown(start: Int) {

        if (start == 0) {
            for(j in start..boolGrid[start].size-1) {
                boolGrid[start][j] = false
            }

        } else {

            for (i in start downTo 1) {
                for (j in 0 until boolGrid[0].size) {
                    var color:Drawable = boxes[i-1][j].background
                    var truthValue = boolGrid[i-1][j]
                    boxes[i][j].background = color
                    boolGrid[i][j] = truthValue

                }
            }

            for (j in 0 until boolGrid[0].size) {
                boolGrid[0][j] = false

            }


        }

    }

    fun setPreferences() {

        if (this.score >= this.highScore) {
            this.e.putInt("highScore", this.score)
            e.commit()
        }
        this.e.putInt("finalScore", this.score)
        e.commit()

    }
    companion object{

        const val HIGH_SCORE : String = "high score"
    }


}