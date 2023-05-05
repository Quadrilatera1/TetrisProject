package com.example.tetrisgroupproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Point
import android.view.View

class GameView : View  {
    lateinit var paint : Paint
    private var gameHeight : Float = 0.0f
    private var gameWidth : Float = 0.0f
    private lateinit var game : Tetris
    constructor(context : Context, width : Int, height : Int ) : super(context) {
        paint = Paint()
        game = Tetris(context)

        gameHeight = height.toFloat()
        gameWidth = width.toFloat()
    }
    fun getGame() : Tetris {
        return game
    }
    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        if (canvas != null){

        }
    }
    companion object {
        const val DELTA_TIME : Int = 50

    }
}