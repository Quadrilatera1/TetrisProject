package com.example.tetrisgroupproject

class OBlock(x:Int, y:Int):Block(x,y) {

    init {
        B = BoardPoint(x, y+1)
        C = BoardPoint(x+1, y)
        D = BoardPoint(x+1, y+1)
    }

    override fun moveDown() {
        this.A.x = this.A.x + 1
        this.B.x = this.B.x + 1
        this.C.x = this.C.x + 1
        this.D.x = this.D.x + 1
    }

    override fun moveLeft() {
        this.A.y = this.A.y-1
        this.B.y = this.B.y-1
        this.C.y = this.C.y-1
        this.D.y = this.D.y-1
    }

    override fun moveRight() {
        this.A.y = this.A.y+1
        this.B.y = this.B.y+1
        this.C.y = this.C.y+1
        this.D.y = this.D.y+1
    }

    override fun rotate() {
        return
    }

    override fun canRotate(boolGrid: Array<Array<Boolean>>): Boolean {
        return false
    }
}