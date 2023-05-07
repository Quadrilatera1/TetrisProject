package com.example.tetrisgroupproject

class TBlock(x:Int, y:Int):Block(x,y) {

    private var state:Int
    init {
        B = BoardPoint(x, y-1)
        C = BoardPoint(x+1, y)
        D = BoardPoint(x, y+1)
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
            D.x = A.x-1
            D.y = A.y
        } else if (state == 1) {
            C.x = A.x
            C.y = A.y+1
        } else if (state == 2) {
            B.x = A.x+1
            B.y = A.y
        } else if (state == 3) {
            B.x = A.x
            B.y = A.y-1
            C.x = A.x+1
            C.y = A.y
            D.x = A.x
            D.y = A.y+1
        }
    }

    override fun canRotate(boolGrid: Array<Array<Boolean>>): Boolean {

        if (state == 0) {
            var nextDX = A.x-1
            var nextDY = A.y

            if (nextDX < 0 || boolGrid[nextDX][nextDY] == true) {
                return false
            }
        } else if (state == 1) {
            var nextCX = A.x
            var nextCY = A.y+1

            if (nextCY > 11 || boolGrid[nextCX][nextCY] == true) {
                return false
            }
        } else if (state == 2) {
            var nextBX = A.x+1
            var nextBY = A.y

            if (nextBX > 23 || boolGrid[nextBX][nextBY] == true) {
                return false
            }
        } else if (state == 3) {

            var nextBX = A.x
            var nextBY = A.y-1
            var nextCX = A.x+1
            var nextCY = A.y
            var nextDX = A.x
            var nextDY = A.y+1

            if (nextBY < 0 || nextCX > 23 || nextDY > 11) {
                return false
            }

            if (boolGrid[nextBX][nextBY] || boolGrid[nextCX][nextCY] || boolGrid[nextDX][nextDY]) {
                return false
            }
        }

        return true
    }
}