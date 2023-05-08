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
import android.widget.TextView
import androidx.core.content.ContextCompat

class GameView : View  {
    private var gameHeight : Float = 0.0f
    private var gameWidth : Float = 0.0f
    private lateinit var game : Tetris
    private lateinit var boxes:Array<Array<Button>>
    lateinit var scoreBox:TextView


    constructor(context : Context, width : Int, height : Int, boxes:Array<Array<Button>>, scoreBox:TextView) : super(context) {

        game = Tetris(context, boxes)
        gameHeight = height.toFloat()
        gameWidth = width.toFloat()

        this.scoreBox = scoreBox
        this.boxes = boxes

    }
    fun getGame() : Tetris {
        return game
    }
    fun updateGrid() {

        scoreBox.text = game.score.toString()

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

        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.BLUE)
        boxes[B.x][B.y].background = ColorDrawable(Color.BLUE)
        boxes[C.x][C.y].background = ColorDrawable(Color.BLUE)
        boxes[D.x][D.y].background = ColorDrawable(Color.BLUE)

    }

    fun drawLBlock() {

        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.parseColor("#FFA500"))
        boxes[B.x][B.y].background = ColorDrawable(Color.parseColor("#FFA500"))
        boxes[C.x][C.y].background = ColorDrawable(Color.parseColor("#FFA500"))
        boxes[D.x][D.y].background = ColorDrawable(Color.parseColor("#FFA500"))

    }

    fun drawOBlock() {

        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.YELLOW)
        boxes[B.x][B.y].background = ColorDrawable(Color.YELLOW)
        boxes[C.x][C.y].background = ColorDrawable(Color.YELLOW)
        boxes[D.x][D.y].background = ColorDrawable(Color.YELLOW)


    }

    fun drawSBlock() {
        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.RED)
        boxes[B.x][B.y].background = ColorDrawable(Color.RED)
        boxes[C.x][C.y].background = ColorDrawable(Color.RED)
        boxes[D.x][D.y].background = ColorDrawable(Color.RED)

    }

    fun drawTBlock() {

        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.parseColor("#9400D3"))
        boxes[B.x][B.y].background = ColorDrawable(Color.parseColor("#9400D3"))
        boxes[C.x][C.y].background = ColorDrawable(Color.parseColor("#9400D3"))
        boxes[D.x][D.y].background = ColorDrawable(Color.parseColor("#9400D3"))

    }

    fun drawZBlock() {

        var A = game.currShape.A
        var B = game.currShape.B
        var C = game.currShape.C
        var D = game.currShape.D

        boxes[A.x][A.y].background = ColorDrawable(Color.GREEN)
        boxes[B.x][B.y].background = ColorDrawable(Color.GREEN)
        boxes[C.x][C.y].background = ColorDrawable(Color.GREEN)
        boxes[D.x][D.y].background = ColorDrawable(Color.GREEN)

    }


    companion object {
        const val DELTA_TIME : Int = 50

    }
}