package com.loogibot.chainfighter

import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*


class MainActivity : AppCompatActivity() {

    // use lateinit to make UI on class load, but before onCreate
    private lateinit var kickButton: Button
    private lateinit var punchButton: Button
    private lateinit var grabButton: Button
    private lateinit var dodgeButton: Button
    private lateinit var shieldButton: Button

    private lateinit var opponentImage: ImageView
    private lateinit var playerImage: ImageView
    private lateinit var playerHP: TextView
    private lateinit var opponentHP: TextView
    private lateinit var playerMove: TextView
    private lateinit var opponentMove: TextView
    private lateinit var playerHPBar: ProgressBar
    private lateinit var opponentHPBar: ProgressBar
    private lateinit var Result: TextView

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

//        Toast.makeText(applicationContext, "SELECT A MOVE", Toast.LENGTH_SHORT).show()

        Result = findViewById(R.id.moveResult)

        playerHPBar = findViewById(R.id.playerHP)
        opponentHPBar = findViewById(R.id.opponentHP)

        playerHP = findViewById(R.id.plyrHpLabel)
        opponentHP = findViewById(R.id.oppHpLabel)

        playerMove = findViewById(R.id.playerMove)
        opponentMove = findViewById(R.id.opponentMove)

        kickButton = findViewById(R.id.kickButton)
        punchButton = findViewById(R.id.punchButton)
        grabButton = findViewById(R.id.grabButton)
        dodgeButton = findViewById(R.id.dodgeButton)
        shieldButton = findViewById(R.id.shieldButton)

        kickButton.text = kick.name
        punchButton.text = punch.name
        grabButton.text = grab.name
        dodgeButton.text = dodge.name
        shieldButton.text = shield.name

        playerHP.text = "PLAYER HP: " + playerHealth.toString()
        opponentHP.text = "OPPONENT HP: " + opponentHealth.toString()

        kickButton.setOnClickListener {
            drawMoves(kick)
        }
        punchButton.setOnClickListener {
            drawMoves(punch)
        }
        grabButton.setOnClickListener {
            drawMoves(grab)
        }
        dodgeButton.setOnClickListener {
            drawMoves(dodge)
        }
        shieldButton.setOnClickListener {
            drawMoves(shield)
        }
    }

    private fun drawMoves(playerChoice: Move) {
        // draws moves when choice is made

        val opponentChoice = randomMoves(opponent)

        opponentImage = findViewById(R.id.opponentChoice)
        playerImage = findViewById(R.id.playerChoice)

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

        playerMove.text = "YOUR MOVE IS: " + playerChoice.name.uppercase(Locale.getDefault())
        opponentMove.text =
            "OPPONENT'S MOVE IS: " + opponentChoice.name.uppercase(Locale.getDefault())

        moveCompare(playerChoice, opponentChoice)
        moveResult()

    }

    private fun moveResult() {
        gameStart()
    }

    private fun moveCompare(player: Move, opponent: Move) {
        if (player.name != opponent.name) {
            if (player.name in opponent.firstAdv || player.name in opponent.secondAdv) {
                Result.text = getString(R.string.opponent_damage)
                opponentHP.text = (opponentHealth - player.damage).toString()

            }
            if (opponent.name in player.firstAdv || opponent.name in player.secondAdv) {
                Result.text = getString(R.string.player_damage)
                playerHP.text = (playerHealth - opponent.damage).toString()

            }
        } else {
            Result.text = getString(R.string.cancel)
        }
    }


    // creates template of all possible moves with name, damage and that move's advantages
    data class Move(
        val name: String,
        val damage: Int,
        val firstAdv: String,
        val secondAdv: String
    ) {}

// For now, below does not need to change, much

    private val kick = Move("kick", 25, "punch", "shield")
    private val grab = Move("grab", 5, "kick", "shield")
    private val dodge = Move("dodge", 0, "kick", "grab")
    private val shield = Move("shield", 5, "punch", "dodge")
    private val punch = Move("punch", 15, "grab", "dodge")

    private val opponent = "opponent"
    private var moveString = "press"
    private val playerHealth = 200
    private val opponentHealth = 200

    private fun moveAvailable(move: Move): String {
        moveString = move.name
        return move.name
    }

    private fun randomMoves(player: String): Move {
        val allMoves = listOf(kick, grab, dodge, shield, punch)
        return when (player) {
            opponent -> allMoves.random()
            else -> allMoves.random()
        }
    }
}