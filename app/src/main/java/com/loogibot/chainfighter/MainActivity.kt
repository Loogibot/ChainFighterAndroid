package com.loogibot.chainfighter

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
    private lateinit var returnToTitleScreen: Button

    private lateinit var opponentImage: ImageView
    private lateinit var playerImage: ImageView

    private lateinit var playerHP: TextView
    private lateinit var opponentHP: TextView
    private lateinit var playerMove: TextView
    private lateinit var opponentMove: TextView
    private lateinit var result: TextView
    private lateinit var finalResult: TextView
    private lateinit var moveDetail: TextView

    private lateinit var playerHPBar: ProgressBar
    private lateinit var opponentHPBar: ProgressBar

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
        // make from UI elements
        result = findViewById(R.id.moveResult)
        moveDetail = findViewById(R.id.move_details)

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

        // relate buttons to moves
        kickButton.text = kick.name
        punchButton.text = punch.name
        grabButton.text = grab.name
        dodgeButton.text = dodge.name
        shieldButton.text = shield.name

        if (turnManager == 0) {
            playerHealth = 200
            opponentHealth = 200
        }

        // button operation
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

        (playerHPLabel + playerHealth.toString()).also { playerHP.text = it }
        (opponentHPLabel + opponentHealth.toString()).also { opponentHP.text = it }

        playerImage.setImageResource(drawPlayerMove)
        opponentImage.setImageResource(drawOpponentMove)

        ("YOUR MOVE IS " + playerChoice.name.uppercase(Locale.getDefault())).also { playerMove.text = it }
        opponentMove.text =
            "OPPONENT'S MOVE IS " + opponentChoice.name.uppercase(Locale.getDefault())

        moveCompare(playerChoice, opponentChoice)

    }

    private fun moveResult() {
        turnManager++
        Toast.makeText(applicationContext, "Turn Number $turnManager", Toast.LENGTH_SHORT).show()

        while (playerHealth > 0 || opponentHealth > 0) {
            gameStart()
            break
        }
        if (playerHealth <= 0) {
            result.text = getString(R.string.plHP0)
            gameEnd(opponent)
        }
        if (opponentHealth <= 0) {
            result.text = getString(R.string.opHP0)
            gameEnd(player)
        }
    }

    private fun moveCompare(player: Move, opponent: Move) {

        if (player.name != opponent.name) {
            // if player is advantageous
            if (player.name in opponent.firstAdv || player.name in opponent.secondAdv) {
                // note the lambda here is the same syntax as opponent win conditional results
                (getString(R.string.opponent_damage) + " -" + player.damage).also { result.text = it }// who takes dam
                (opponent.name.uppercase() + isWeakToText + player.name.uppercase()).also { moveDetail.text = it }
                plHPDam += player.damage
                opponentHealth -= player.damage
                (opponentHPLabel + opponentHealth.toString()).also { opponentHP.text = it }
                opponentHPBar.progress = opponentHealth
            }
            // if opponent is advantageous
            else if (opponent.name in player.firstAdv || opponent.name in player.secondAdv) {
                result.text = getString(R.string.player_damage) + " -" + opponent.damage// who takes dam
                moveDetail.text = player.name.uppercase() + isWeakToText + opponent.name.uppercase()
                opHPDam += opponent.damage
                playerHealth -= opponent.damage
                playerHP.text = playerHPLabel + playerHealth.toString()
                playerHPBar.progress = playerHealth
            }
        } else {
            result.text = getString(R.string.cancel)
        }
        moveResult()
    }

    private fun gameEnd(final: String) {
        setContentView(R.layout.endgamepage)

        finalResult = findViewById(R.id.final_result)
        returnToTitleScreen = findViewById(R.id.to_titlescreen)

        when (final) {
            player -> finalResult.text = getString(R.string.you_won)
            opponent -> finalResult.text = getString(R.string.opponent_won)
        }

        returnToTitleScreen.setOnClickListener{
            recreate()
        }
    }

    // creates template of all possible moves with name, damage and that move's advantages
    data class Move(
        val name: String,
        val damage: Int,
        val firstAdv: String,
        val secondAdv: String
    )

// For now, below does not need to change, much
    private val kick = Move("kick", 25, "punch", "shield")
    private val grab = Move("grab", 5, "kick", "shield")
    private val dodge = Move("dodge", 5, "kick", "grab")
    private val shield = Move("shield", 5, "punch", "dodge")
    private val punch = Move("punch", 15, "grab", "dodge")

    private val opponent = "opponent"
    private val player = "player"
    private val opponentHPLabel = "OPPONENT HP IS "
    private val playerHPLabel = "PLAYER HP IS "
    private val isWeakToText = " IS WEAK TO "

    private var plHPDam = 0
    private var opHPDam = 0
    private var playerHealth = 200
    private var opponentHealth = 200
    private var turnManager = 0

    private fun randomMoves(player: String): Move {
        val allMoves = listOf(kick, grab, dodge, shield, punch)
        return when (player) {
            opponent -> allMoves.random()
            else -> allMoves.random()
        }
    }
}