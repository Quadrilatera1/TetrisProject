package com.example.tetrisgroupproject

class IBlock(x:Int, y:Int):Block(x,y) {

    init {
        B = BoardPoint(x+1, y)
        C = BoardPoint(x+2, y)
        D = BoardPoint(x+3, y)
    }

    override fun moveDown() {
        TODO("Not yet implemented")
    }

    override fun moveLeft() {
        TODO("Not yet implemented")
    }

    override fun moveRight() {
        TODO("Not yet implemented")
    }

    override fun rotate() {
        TODO("Not yet implemented")
    }
}