package com.example.tetrisgroupproject

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Point
import android.widget.Button
import android.widget.GridLayout
import java.util.*
import kotlin.math.*

class Tetris {
    private var random: Random? = null
    private var score: Int = 0
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
        random = Random()
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
        this.currShape = IBlock(0, 5)
    }

    fun moveShapeDown() {

        this.currShape.moveDown()

    }

    fun moveShapeLeft() {
        currShape.moveLeft()
    }

    fun moveShapeRight() {
        currShape.moveRight()
    }

    fun rotateShape() {
        //Calculate a projection of the shape's rotation using its coordinates
        //If the projection is entirely within the grid, then call currShape.rotate()
    }

    fun blockShouldStop():Boolean {
        return false
    }

    fun setBlockRestingPlace() {

    }

    fun rowIsFull() : Boolean {
        return false
    }

    fun clearRow() {

    }

    fun moveAboveRowsDown() {

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