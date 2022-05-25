package com.loogibot.chainfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


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
//    private lateinit var playerHPBar: ProgressBar
//    private lateinit var opponentHPBar: ProgressBar
//    private lateinit var Result: TextView

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

        Toast.makeText(applicationContext, "SELECT A MOVE", Toast.LENGTH_SHORT).show()

        playerHP = findViewById(R.id.plyrHpLabel)
        opponentHP = findViewById(R.id.oppHpLabel)

        playerMove = findViewById(R.id.playerMove)
        opponentMove = findViewById(R.id.opponentMove)

        kickButton = findViewById(R.id.kickButton)
        punchButton = findViewById(R.id.punchButton)
        grabButton = findViewById(R.id.grabButton)
        dodgeButton = findViewById(R.id.dodgeButton)
        shieldButton = findViewById(R.id.shieldButton)

        val kickMove = moveAvailable(kick)
        val punchMove = moveAvailable(punch)
        val grabMove = moveAvailable(grab)
        val dodgeMove = moveAvailable(dodge)
        val shieldMove = moveAvailable(shield)

        kickButton.text = kickMove
        punchButton.text = punchMove
        grabButton.text = grabMove
        dodgeButton.text = dodgeMove
        shieldButton.text = shieldMove

        val playerHealth = 200
        val opponentHealth = 200
        playerHP.text = "PLAYER HP: " + playerHealth.toString()
        opponentHP.text = "OPPONENT HP: " + opponentHealth.toString()

        kickButton.setOnClickListener {
            drawMoves(kickMove)
        }

        punchButton.setOnClickListener {
            drawMoves(punchMove)
        }

        grabButton.setOnClickListener {
            drawMoves(grabMove)
        }

        dodgeButton.setOnClickListener {
            drawMoves(dodgeMove)
        }

        shieldButton.setOnClickListener {
            drawMoves(shieldMove)
        }

    }

    private fun drawMoves(playerChoice: String) {
        // draws moves when choice is made

        val opponentChoice = randomMoves(opponent)

        opponentImage = findViewById(R.id.opponentChoice)
        playerImage = findViewById(R.id.playerChoice)

        val drawPlayerMove = when (playerChoice) {
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

        playerMove.text = playerChoice
        opponentMove.text = opponentChoice.name

        moveResult()

    }

    private fun moveResult() {

        gameStart()
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