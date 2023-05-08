package com.example.tetrisgroupproject

class IBlock(x:Int, y:Int):Block(x,y) {

    private var state:Int

    init {
        B = BoardPoint(x-1, y)
        C = BoardPoint(x+1, y)
        D = BoardPoint(x+2, y)
        state = 0

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
        if (state == 0) {
            B.x = A.x
            B.y = A.y-1
            C.x = A.x
            C.y = A.y+1
            D.x = A.x
            D.y = A.y+2
            state = 1
        } else if (state == 1) {
            B.y = A.y
            B.x = A.x-1
            C.y = A.y
            C.x = A.x+1
            D.y = A.y
            D.x = A.x+2
            state = 0

        }
    }

    override fun canRotate(boolGrid: Array<Array<Boolean>>): Boolean {
        if (state == 0) {

            var newBY = this.A.y-1
            var newCY = this.A.y+1
            var newDY = this.A.y+2

            if (newBY < 0 || newCY > 10 || newDY > 10) {
                return false
            }

            if (boolGrid[this.A.x][newBY] == true
                || boolGrid[this.A.x][newCY] == true
                || boolGrid[this.A.x][newDY] == true) {
                return  false
            }
        } else if (state == 1) {

            var newBX = this.A.x-1
            var newCX = this.A.x+1
            var newDX = this.A.x+2

            if (newBX < 0 || newCX > 23 || newDX > 23) {
                return false
            }

            if (boolGrid[newBX][this.A.y] == true
                || boolGrid[newCX][this.A.y] == true
                || boolGrid[newDX][this.A.y] == true) {
                return  false
            }
        }
        return true
    }
}