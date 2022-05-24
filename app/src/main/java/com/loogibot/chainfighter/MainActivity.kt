package com.loogibot.chainfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.ImageView
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private val playerButtonOne: Button = findViewById(R.id.moveOne)
    private val playerButtonTwo: Button = findViewById(R.id.moveTwo)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.titlewindow)
        // switch activity layout for this implementation

        val startButton: Button = findViewById(R.id.startGame)
        startButton.setOnClickListener {
        setContentView(R.layout.activity_main)
        gameStart()
        } // starts game
    }

    private fun gameStart () {

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


        // playerButtonOne.text = moveText
        // playerButtonTwo.text = moveText
    }

    class Fighter(val hP: Int = 200,val position: String, val gameTurn: Int = 0) {
        // sets up beginning stats and turn


        }

    data class Move( val name: String, val damage: Int, val firstAdv: String, val secondAdv: String ) {
        // creates template of all possible moves with name, damage and that move's advantages
        }

}

val kick = MainActivity.Move("kick", 25, "punch", "shield")
val grab = MainActivity.Move("grab", 5, "kick", "shield")
val dodge = MainActivity.Move("dodge", 0, "kick", "grab")
val shield = MainActivity.Move("shield", 5, "punch", "dodge")
val punch = MainActivity.Move("punch", 15, "grab", "dodge")

val player1 = "player_one"
val player2 = "player_two"
val opponent = "opponent"

var moveString = " "

fun moveAvailable(player: String): MainActivity.Move {
    val playingMove = randomMoves(player)
    moveString = playingMove.name
    return playingMove
}

fun randomMoves(player: String): MainActivity.Move {
    val allMoves = listOf(kick, grab, dodge, shield, punch)

    return when (player) {
        "player_one" -> allMoves.random()
        "player_two" -> allMoves.random()
        else -> allMoves.random()
    }
}