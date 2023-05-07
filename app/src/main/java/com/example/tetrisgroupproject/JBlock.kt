package com.example.tetrisgroupproject

class JBlock(x:Int, y:Int):Block(x,y) {

    private var state:Int
    init {
        B = BoardPoint(x-1, y)
        C = BoardPoint(x+1, y)
        D = BoardPoint(x+1, y-1)
        state = 0
    }

    override fun moveDown() {
        this.A.x = this.A.x + 1
        this.B.x = this.B.x + 1
        this.C.x = this.C.x + 1
        this.D.x = this.D.x + 1
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

    override fun canRotate(boolGrid: Array<Array<Boolean>>): Boolean {
        return true
    }

}