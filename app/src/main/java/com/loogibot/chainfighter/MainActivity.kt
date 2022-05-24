package com.loogibot.chainfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    // use lateinit to make UI on class load, but before onCreate
    private lateinit var playerButtonOne: Button
    private lateinit var playerButtonTwo: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.titlewindow)
        // switch activity layout for this implementation
        // there's a better way by making the title window its own activity,
        // but this is fine fow now

        val startButton: Button = findViewById(R.id.startGame)
        startButton.setOnClickListener {
            setContentView(R.layout.activity_main)
            gameStart()
        } // starts game
    }

    private fun gameStart() {

        playerButtonOne = findViewById(R.id.moveOne)
        playerButtonTwo = findViewById(R.id.moveTwo)

        playerButtonOne.text = moveString
        playerButtonTwo.text = moveString

        playerButtonOne.setOnClickListener {
            drawMoves(moveAvailable(player1))
        }

        playerButtonTwo.setOnClickListener {
            drawMoves(moveAvailable(player2))
        }

    }

    private fun drawMoves(playerChoice: Move) {
        // draws moves when choice is made

        val opponentChoice = moveAvailable(opponent)

        val opponentImage: ImageView = findViewById(R.id.opponentChoice)
        val playerImage: ImageView = findViewById(R.id.playerChoice)

        val drawPlayerMove = when (playerChoice.name) {
            "kick" -> R.drawable.player_kick
            "punch" -> R.drawable.player_punch
            "dodge" -> R.drawable.player_dodge
            "grab" -> R.drawable.player_grab
            "shield" -> R.drawable.player_shield
            else -> R.drawable.none_image
        }

        val drawOpponentMove = when (opponentChoice.name) {
            "kick" -> R.drawable.player_kick
            "punch" -> R.drawable.player_punch
            "dodge" -> R.drawable.player_dodge
            "grab" -> R.drawable.player_grab
            "shield" -> R.drawable.player_shield
            else -> R.drawable.none_image
        }

        playerImage.setImageResource(drawPlayerMove)
        opponentImage.setImageResource(drawOpponentMove)

        playerButtonOne.text = playerChoice.name
        playerButtonTwo.text = playerChoice.name

    }

    data class Move(
        val name: String,
        val damage: Int,
        val firstAdv: String,
        val secondAdv: String
    ) {
        // creates template of all possible moves with name, damage and that move's advantages
    }

// For now, below does not need to change, much

    private val kick = Move("kick", 25, "punch", "shield")
    private val grab = Move("grab", 5, "kick", "shield")
    private val dodge = Move("dodge", 0, "kick", "grab")
    private val shield = Move("shield", 5, "punch", "dodge")
    private val punch = Move("punch", 15, "grab", "dodge")

    private val player1 = "player_one"
    private val player2 = "player_two"
    private val opponent = "opponent"

    private var moveString = "press"

    private fun moveAvailable(player: String): Move {
        val playingMove = randomMoves(player)
        moveString = playingMove.name
        return playingMove
    }

    private fun randomMoves(player: String): Move {

        val allMoves = listOf(kick, grab, dodge, shield, punch)
        return when (player) {
            "player_two" -> allMoves.random()
            "player_one" -> allMoves.random()
            else -> allMoves.random()
        }
    }
}