package com.example.diceyroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var randomNumber: Int = 0
    var scorePlayerA: Int = 0
    var scorePlayerB: Int = 0
    var activePlayerA: Boolean = true
    var activePlayerB: Boolean = false
    var gameEndMsg: String? = null
    var gameEndMsgDefault: String? = null
    var gameStartMsg: String? = null
    var gameStopMsg: String? = null

    private fun startGame() {
        dice_image.isEnabled = false
        gameStartMsg = resources.getText(R.string.btn_start_game).toString()
        btnGamePlay.text = gameStartMsg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startGame()

        btnGamePlay.setOnClickListener {
            dice_image.isEnabled = true

            activePlayerA = true
            activePlayerB = false

            scorePlayerA = 0
            tvGamePointA.text = scorePlayerA.toString()

            scorePlayerB = 0
            tvGamePointB.text = scorePlayerB.toString()

            tvGameOverMsg.text = ""
            dice_image.setImageResource(R.drawable.dice1)

            gameStopMsg = resources.getText(R.string.btn_stop_game).toString()
            btnGamePlay.text = gameStopMsg
        }

        dice_image.setOnClickListener {

            randomNumber = (1..6).random()

            when (randomNumber) {
                1 -> {
                    dice_image.setImageResource(R.drawable.dice1)
                }
                2 -> {
                    dice_image.setImageResource(R.drawable.dice2)
                }
                3 -> {
                    dice_image.setImageResource(R.drawable.dice3)
                }
                4 -> {
                    dice_image.setImageResource(R.drawable.dice4)
                }
                5 -> {
                    dice_image.setImageResource(R.drawable.dice5)
                }
                6 -> {
                    dice_image.setImageResource(R.drawable.dice6)
                }
            }

            if (activePlayerA) {
                scorePlayerA += randomNumber
                tvGamePointA.text = scorePlayerA.toString()
                activePlayerA = false
                activePlayerB = true

                if (scorePlayerA >= 100) {
                    gameEndMsgDefault = resources.getText(R.string.game_over_msg).toString()
                    gameEndMsg = "$gameEndMsgDefault Player A"
                    tvGameOverMsg.text = gameEndMsg

                    startGame()
                }
            } else {
                scorePlayerB += randomNumber
                tvGamePointB.text = scorePlayerB.toString()
                activePlayerB = false
                activePlayerA = true

                if (scorePlayerB >= 100) {
                    gameEndMsgDefault = resources.getText(R.string.game_over_msg).toString()
                    gameEndMsg = "$gameEndMsgDefault Player B"
                    tvGameOverMsg.text = gameEndMsg

                    startGame()
                }
            }
        }
    }
}
