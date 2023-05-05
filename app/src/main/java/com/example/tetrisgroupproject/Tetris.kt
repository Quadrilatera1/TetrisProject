package com.example.tetrisgroupproject

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import java.util.*
import kotlin.math.*

class Tetris {
    private var random: Random? = null
    private var score: Int = 0
    private var highScore: Int = 0
    private var gameOver : Boolean = false
    private lateinit var currShape : Shape
    private lateinit var nextShape : Shape
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var pref: SharedPreferences

    val LINES = 22
    val COLUMNS = 12


    constructor(context: Context){
        random = Random()
        var pref : SharedPreferences =
            context!!.getSharedPreferences( context.packageName +
                    "_preferences", Context.MODE_PRIVATE )
        setHighScore(pref.getInt(HIGH_SCORE, 0))
    }
    fun setHighScore(newHighScore: Int) : Unit {
        highScore = newHighScore
    }
    fun getHighScore() : Int{
        return highScore
    }

    fun moveShapeDown() {

        this.currShape.moveShapeDown()

    }

    fun moveShapeLeft() {
        this.currShape.moveShapeLeft()
    }

    fun moveShapeRight() {
        this.currShape.moveShapeRight()
    }

    fun rowIsFull() : Boolean {
        return false
    }

    fun hasTetris() : Boolean {
        return true
    }

    fun doTetris() {

    }

    fun clearRow() {

    }

    fun moveAboveRowsDown() {

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