package com.example.tetrisgroupproject

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Point
import android.widget.Button
import android.widget.GridLayout
import java.util.*
import kotlin.math.*
import kotlin.random.*

class Tetris {

    var score: Int = 0
    private var highScore: Int = 0
    private var gameOver : Boolean = false
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var pref: SharedPreferences
    lateinit var boxes:Array<Array<Button>>
    lateinit var boolGrid:Array<Array<Boolean>>
    lateinit var currShape:Block
    lateinit var activity: TetrisActivity

    val LINES = 22
    val COLUMNS = 12


    constructor(context: Context, boxes:Array<Array<Button>>){

        var pref : SharedPreferences =
            context!!.getSharedPreferences( context.packageName +
                    "_preferences", Context.MODE_PRIVATE )
        setHighScore(pref.getInt(HIGH_SCORE, 0))

        this.boxes = boxes

        this.boolGrid = Array(24, {i->Array<Boolean>(11, {j->false})})

    }
    fun setHighScore(newHighScore: Int) : Unit {
        highScore = newHighScore
    }
    fun getHighScore() : Int{
        return highScore
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
        if (boolGrid[currShape.A.x][currShape.A.y] == false
            && boolGrid[currShape.B.x][currShape.B.y] == false
            && boolGrid[currShape.C.x][currShape.C.y] == false
            && boolGrid[currShape.D.x][currShape.D.y] == false) {
            return true
        }
        return false
    }

    fun placementValid():Boolean {
        var A = currShape.A
        var B = currShape.B
        var C = currShape.C
        var D = currShape.D

        if (A.x < 0 || A.x < 0 || A.x < 0 || A.x < 0) {
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

        if (A.y-1 < 0 || B.y-1 < 0 || C.y-1 < 0 || D.y-11 < 0) {
            return false
        }
        if (boolGrid[A.x][A.y-1] || boolGrid[B.x][B.y-1] || boolGrid[C.x][C.y-1] || boolGrid[D.x][D.y-1]) {
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

        if (A.y+1 >= 12 || B.y+1 >= 12 || C.y+1 >= 12 || D.y+1 >= 12) {
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
        if (currShape.canRotate(boolGrid)) {
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
            return
        }

        for (i in start..boolGrid.size-1) {
            for (j in 0..boolGrid[start].size-1) {
                if (boolGrid[i-1][j] == true) {
                    var color = boxes[i-1][j].background
                    boxes[i][j].background = color
                    boolGrid[i][j] = true
                    boolGrid[i-1][j] = false
                }
            }
        }

    }

    fun gameOver():Boolean {
        return false
    }

    fun setPreferences( context : Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences",
                Context.MODE_PRIVATE )
        editor = pref.edit()
        editor.putInt(HIGH_SCORE, score)
        editor.commit()

    }
    companion object{

        const val HIGH_SCORE : String = "high score"
    }


}