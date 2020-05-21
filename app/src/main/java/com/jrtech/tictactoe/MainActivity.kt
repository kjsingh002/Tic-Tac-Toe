package com.jrtech.tictactoe

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var playAgain: Button? = null
    private var displayWinner: TextView? = null
    private var player: Int = 0
    private var startingState: IntArray = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var gameRunning: Boolean = true
    private var gridLayout: GridLayout? = null
    private val winningPositions: Array<IntArray> = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayWinner = findViewById(R.id.display_winner)
        playAgain = findViewById(R.id.play_again)
        gridLayout = findViewById(R.id.my_grid_layout)
        playAgain!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                displayWinner?.text = ""
                gameRunning = true
                playAgain!!.visibility = View.INVISIBLE
                startingState = intArrayOf(2,2,2,2,2,2,2,2,2)
                for (x in 0 until gridLayout!!.childCount){
                    val image: ImageView = gridLayout!!.getChildAt(x) as ImageView
                    image.setImageDrawable(null)
                }
            }
        })
    }

    fun rollCoin(view: View) {
        val tapped: ImageView = view as ImageView
        val tappedTag = tapped.tag
        if (startingState[tappedTag.toString().toInt()] == 2 && gameRunning) {
            startingState[tappedTag.toString().toInt()] = player
            tapped.translationY = -1500F
            if (player == 0) {
                tapped.setImageResource(R.drawable.red)
                player = 1
            } else {
                tapped.setImageResource(R.drawable.yellow)
                player = 0
            }
            tapped.animate().translationYBy(1500F).duration = 200
            for (winningPosition in winningPositions) {
                if (startingState[winningPosition[0]] == startingState[winningPosition[1]] && startingState[winningPosition[1]] == startingState[winningPosition[2]] && startingState[winningPosition[0]] != 2
                ) {
                    gameRunning = false
                    playAgain!!.visibility = View.VISIBLE
                    if (player == 1){
                        displayWinner?.text = "Red has won"
                    }else{
                        displayWinner?.text = "Yellow has Won"
                    }
                }
            }
        }
    }
}
