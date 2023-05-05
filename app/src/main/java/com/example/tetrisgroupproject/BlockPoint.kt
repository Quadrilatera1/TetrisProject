package com.example.tetrisgroupproject

class BlockPoint(var x:Int, var y:Int) {

    fun movePointDown() {
        x++
    }

    fun movePointRight() {
        y++
    }

    fun movePointLeft() {
        y--
    }

}