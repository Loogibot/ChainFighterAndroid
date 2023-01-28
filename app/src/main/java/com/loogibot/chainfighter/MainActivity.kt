package com.loogibot.chainfighter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.m
import com.loogibot.chainfighter.player.Players
import com.loogibot.chainfighter.ui.drawMoves

class MainActivity : AppCompatActivity() {

    // use late init to make UI on class load, but before onCreate
    private lateinit var kickButton: Button
    private lateinit var punchButton: Button
    private lateinit var grabButton: Button
    private lateinit var dodgeButton: Button
    private lateinit var shieldButton: Button
    private lateinit var returnToTitleScreen: Button

    private lateinit var playerHP: TextView
    private lateinit var opponentHP: TextView
    private lateinit var playerMove: TextView
    private lateinit var opponentMove: TextView
    private lateinit var result: TextView
    private lateinit var finalResult: TextView
    private lateinit var moveDetail: TextView

    private lateinit var playerHPBar: ProgressBar
    private lateinit var opponentHPBar: ProgressBar

    //Bring in MoveData

    private val p = Players()

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

        if (p.turnManager == 0) {
            p.playerHealth = 200
            p.opponentHealth = 200
        }

        // button operation
        kickButton.setOnClickListener {
            drawMoves(m.kick)
        }
        punchButton.setOnClickListener {
            drawMoves(m.punch)
        }
        grabButton.setOnClickListener {
            drawMoves(m.grab)
        }
        dodgeButton.setOnClickListener {
            drawMoves(m.dodge)
        }
        shieldButton.setOnClickListener {
            drawMoves(m.shield)
        }
    }


    private fun moveResult() {
        p.turnManager++
        Toast.makeText(applicationContext, "Turn Number $p.turnManager", Toast.LENGTH_SHORT).show()

        while (p.playerHealth > 0 || p.opponentHealth > 0) {
            gameStart()
            break
        }
        if (p.playerHealth <= 0) {
            result.text = getString(R.string.plHP0)
            gameEnd(p.opponent)
        }
        if (p.opponentHealth <= 0) {
            result.text = getString(R.string.opHP0)
            gameEnd(p.player)
        }
    }

    private fun moveCompare(player: Move, opponent: Move) {

        if (player.name != opponent.name) {
            // if player is advantageous
            if (player.name in opponent.firstAdv || player.name in opponent.secondAdv) {
                // note the lambda here is the same syntax as opponent win conditional results
                (getString(R.string.opponent_damage) + " -" + player.damage).also {
                    result.text = it
                }// who takes dam
                (opponent.name.uppercase() + p.isWeakToText + player.name.uppercase()).also {
                    moveDetail.text = it
                }
                p.plHPDam += player.damage
                p.opponentHealth -= player.damage
                (p.opponentHPLabel + p.opponentHealth.toString()).also { opponentHP.text = it }
                opponentHPBar.progress = p.opponentHealth
            }
            // if opponent is advantageous
            else if (opponent.name in player.firstAdv || opponent.name in player.secondAdv) {
                "${this.getString(R.string.player_damage)} -${opponent.damage}".also {
                    result.text = it
                }// who takes dam
                moveDetail.text = buildString {
                    append(player.name.uppercase())
                    append(p.isWeakToText)
                    append(opponent.name.uppercase())
                }
                p.opHPDam += opponent.damage
                p.playerHealth -= opponent.damage
                "$p.playerHPLabel$p.playerHealth".also { playerHP.text = it }
                playerHPBar.progress = p.playerHealth
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
            p.player -> finalResult.text = getString(R.string.you_won)
            p.opponent -> finalResult.text = getString(R.string.opponent_won)
        }

        returnToTitleScreen.setOnClickListener {
            recreate()
        }
    }

}