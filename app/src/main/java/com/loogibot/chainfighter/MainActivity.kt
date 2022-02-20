package com.loogibot.chainfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.titlewindow)

        val startButton: Button = findViewById(R.id.startGame)
        startButton.setOnClickListener{setContentView(R.layout.activity_main)}

    }

    private fun moveChoice(movePos: Int) {

        val playerButtonOne: Button = findViewById(R.id.moveOne)
        playerButtonOne.setOnClickListener {moveChoice(1)}

        val playerButtonTwo: Button = findViewById(R.id.moveTwo)
        playerButtonTwo.setOnClickListener {moveChoice(2)}

        val opponentMove = MoveAvailable(3)
        val opponentChoice = opponentMove.moveCycle()
        val opponentImage: ImageView = findViewById(R.id.opponentChoice)

        val playerMove = MoveAvailable(movePos)
        val playerChoice = playerMove.moveCycle()

        val playerImage: ImageView = findViewById(R.id.playerChoice)

        val drawPlayerMove = when (playerChoice.name) {
            "kick" -> R.drawable.player_kick
            "punch" -> R.drawable.player_punch
            "dodge" -> R.drawable.player_dodge
            "grab" -> R.drawable.player_grab
            else -> R.drawable.player_shield
        }

        playerImage.setImageResource(drawPlayerMove)
        opponentChoice

    }
}

data class Move(val name: String, val damage: Int, val firstAdv: String, val secondAdv: String) {
}

class MoveAvailable(private val movePos: Int) {

    val kick = Move("kick",25, "punch", "shield")
    val grab = Move("grab",5, "kick", "shield")
    val dodge = Move("dodge",0, "kick", "grab")
    val shield = Move("shield",5, "punch", "dodge")
    val punch = Move("punch",15, "grab", "dodge")

    val allMoves = listOf(kick,grab,dodge,shield,punch)

    fun moveCompare(player: String, opponent: String) {
        if (player == opponent) {
            return
        }
    }

    fun moveCycle(): Move {
        return when (movePos) {
        1 -> allMoves.random()
        2 -> allMoves.random()
        else -> allMoves.random()
            }
    }
}
