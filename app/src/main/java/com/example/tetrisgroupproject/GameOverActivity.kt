package com.example.tetrisgroupproject

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class GameOverActivity : Activity() {


    lateinit var sp:SharedPreferences
    lateinit var e:SharedPreferences.Editor
    lateinit var leaderBoardsButton: Button
    lateinit var playAgainButton:Button
    lateinit var newHighScoreBox:TextView
    lateinit var finalScoreBox:TextView

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)

        sp = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        e = sp.edit()

        setContentView(R.layout.activity_game_over)

        var highScore = TetrisActivity.game.getHighScore()
        var finalScore = TetrisActivity.game.getFinalScore()

        playAgainButton = findViewById<Button>(R.id.playAgain)
        leaderBoardsButton = findViewById<Button>(R.id.leaderboards)
        finalScoreBox = findViewById(R.id.finalBox)
        newHighScoreBox = findViewById(R.id.endHighScoreBox)



        if (finalScore >= highScore) {
            newHighScoreBox.text = "New High Score!: "+finalScore
            finalScoreBox.text = "Thanks for Playing!"


        } else {
            finalScoreBox.text = "Final Score: "+finalScore
            newHighScoreBox.text = "Previous High Score: "+highScore
        }

        playAgainButton.setOnClickListener {
            playAgain()
        }

        leaderBoardsButton.setOnClickListener {
            goToLeaderBoards()
        }
    }

    fun playAgain() {
        var myIntent : Intent = Intent( this, TetrisActivity::class.java )
        var b:Bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        startActivity( myIntent, b )
    }

    fun goToLeaderBoards() {

    }




}