package com.example.tetrisgroupproject

public abstract class Block(var x:Int, var y:Int) {

    var A = BoardPoint(x, y)
    lateinit var B:BoardPoint
    lateinit var C:BoardPoint
    lateinit var D:BoardPoint

    abstract fun moveDown()
    abstract fun moveLeft()
    abstract fun moveRight()
    abstract fun rotate()




}