package com.example.tetrisgroupproject

abstract class Shape(var x:Int, var y:Int) {

    var p1 = BlockPoint(x, y)
    lateinit var p2:BlockPoint
    lateinit var p3:BlockPoint
    lateinit var p4:BlockPoint

    abstract fun rotate()
    abstract fun moveShapeDown()
    abstract fun moveShapeRight()
    abstract fun moveShapeLeft()

}