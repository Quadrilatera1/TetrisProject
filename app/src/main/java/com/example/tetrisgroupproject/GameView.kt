package com.example.tetrisgroupproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.core.content.ContextCompat

class GameView : View  {
    private var gameHeight : Float = 0.0f
    private var gameWidth : Float = 0.0f
    private lateinit var game : Tetris
    private lateinit var boxes:Array<Array<Button>>


    constructor(context : Context, width : Int, height : Int, boxes:Array<Array<Button>>) : super(context) {

        game = Tetris(context, boxes)
        gameHeight = height.toFloat()
        gameWidth = width.toFloat()

        this.boxes = boxes

    }
    fun getGame() : Tetris {
        return game
    }
    fun updateGrid() {

        for (i in 0..game.boolGrid.size-1) {
            for (j in 0..game.boolGrid[i].size-1) {
                if (game.boolGrid[i][j] == false) {
                    boxes[i][j].background = ColorDrawable(Color.WHITE)
                }
            }
        }

        if (game.currShape is IBlock) {
            drawIBlock()
        }
        if (game.currShape is LBlock) {
            drawLBlock()
        }
        if (game.currShape is JBlock) {
            drawJBlock()
        }
        if (game.currShape is OBlock) {
            drawOBlock()
        }
        if (game.currShape is SBlock) {
            drawSBlock()
        }
        if (game.currShape is TBlock) {
            drawTBlock()

        }
        if (game.currShape is ZBlock) {
            drawZBlock()
        }
    }

    fun drawIBlock() {
        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D


        boxes[A.x][A.y].background = ColorDrawable(Color.CYAN)
        boxes[B.x][B.y].background = ColorDrawable(Color.CYAN)
        boxes[C.x][C.y].background = ColorDrawable(Color.CYAN)
        boxes[D.x][D.y].background = ColorDrawable(Color.CYAN)
    }

    fun drawJBlock() {

    }

    fun drawLBlock() {

    }

    fun drawOBlock() {

    }

    fun drawSBlock() {

    }

    fun drawTBlock() {

    }

    fun drawZBlock() {

    }


    companion object {
        const val DELTA_TIME : Int = 50

    }
}