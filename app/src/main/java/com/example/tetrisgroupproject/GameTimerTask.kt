package com.example.tetrisgroupproject

import java.util.*
class GameTimerTask : TimerTask{
    private lateinit var activity : TetrisActivity

    constructor( tetrisActivity : TetrisActivity ) {
        activity = tetrisActivity
    }

    override fun run( ) {
        // update the Model
        activity.updateModel( )
        // update the View
        activity.updateView( )
    }

}