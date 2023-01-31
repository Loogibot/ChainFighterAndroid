package com.loogibot.chainfighter

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.moves.MoveSource.M.m
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

        opponentImage = findViewById(R.id.opponentChoice)
        playerImage = findViewById(R.id.playerChoice)

        val uIObjectsList: List<Any> = listOf(
            result,
            moveDetail,
            playerHPBar,
            opponentHPBar,
            playerHP,
            opponentHP,
            playerMove,
            opponentMove,
            playerImage,
            opponentImage
        )

        if (Players.turnManager == 0) {
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }

        // button operation
        kickButton.setOnClickListener {
            drawMoves(m.kick, uIObjectsList)
        }
        punchButton.setOnClickListener {
            drawMoves(m.punch, uIObjectsList)
        }
        grabButton.setOnClickListener {
            drawMoves(m.grab, uIObjectsList)
        }
        dodgeButton.setOnClickListener {
            drawMoves(m.dodge, uIObjectsList)
        }
        shieldButton.setOnClickListener {
            drawMoves(m.shield, uIObjectsList)
        }

    }

    fun moveResult(result: TextView) {
        Players.turnManager++

        while (Players.playerHealth > 0 || Players.opponentHealth > 0) run {
            gameStart()
        }
        if (Players.playerHealth <= 0) {
            result.text = R.string.plHP0.toString()
            gameEnd(Players.opponent)
        }
        if (Players.opponentHealth <= 0) {
            result.text = R.string.opHP0.toString()
            gameEnd(Players.player)
        }
    }

    private fun gameEnd(final: String) {
        setContentView(R.layout.endgamepage)

        finalResult = findViewById(R.id.final_result)
        returnToTitleScreen = findViewById(R.id.to_titlescreen)

        when (final) {
            Players.player -> finalResult.text = getString(R.string.you_won)
            Players.opponent -> finalResult.text = getString(R.string.opponent_won)
        }
        returnToTitleScreen.setOnClickListener {
            recreate()
        }
    }
}