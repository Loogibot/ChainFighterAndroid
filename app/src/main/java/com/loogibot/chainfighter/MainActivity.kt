package com.loogibot.chainfighter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.databinding.ActivityMainBinding
import com.loogibot.chainfighter.moves.MoveSource.M.m
import com.loogibot.chainfighter.player.Players
import com.loogibot.chainfighter.ui.drawMoves

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.titlewindow)
        // switch activity layout for this implementation
        // there's a better way by making the title window its own activity,
        // but this is fine fow now

        val startButton: Button = findViewById(R.id.startGame)
        startButton.setOnClickListener {
            setContentView(binding.root)
            gameStart()
        } // starts game
    }

    private fun gameStart() {
        // make from UI elements

        val uIObjectsList: List<Any> = listOf(
            binding.playerView.moveResult,
            binding.opponentView.moveDetails,
            binding.playerView.playerHPBar,
            binding.opponentView.opponentHPBar,
            binding.playerView.plyrHpLabel,
            binding.opponentView.oppHpLabel,
            binding.opponentView.opponentChoiceImg,
            binding.playerView.playerChoiceImg
        )

        if (Players.turnManager == 0) {
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }

        // button operation
        binding.moveButtonView.kickButton.setOnClickListener {
            drawMoves(m.kick, uIObjectsList)
        }
        binding.moveButtonView.punchButton.setOnClickListener {
            drawMoves(m.punch, uIObjectsList)
        }
        binding.moveButtonView.grabButton.setOnClickListener {
            drawMoves(m.grab, uIObjectsList)
        }
        binding.moveButtonView.dodgeButton.setOnClickListener {
            drawMoves(m.dodge, uIObjectsList)
        }
        binding.moveButtonView.shieldButton.setOnClickListener {
            drawMoves(m.shield, uIObjectsList)
        }

    }

    fun moveResult() {
        Players.turnManager++

        while (Players.playerHealth > 0 || Players.opponentHealth > 0) run {
            gameStart()
        }
        if (Players.playerHealth <= 0) {
            binding.playerView.moveResult.text = R.string.plHP0.toString()
            //gameEnd(Players.opponent)
        }
        if (Players.opponentHealth <= 0) {
            binding.playerView.moveResult.text = R.string.opHP0.toString()
            //gameEnd(Players.player)
        }
    }

//    private fun gameEnd(final: String) {
//        setContentView(R.layout.endgamepage)
//
//        finalResult = findViewById(R.id.final_result)
//        returnToTitleScreen = findViewById(R.id.to_titlescreen)
//
//        when (final) {
//            (Players.player) -> finalResult.text = getString(R.string.you_won)
//            (Players.opponent) -> finalResult.text = getString(R.string.opponent_won)
//        }
//        returnToTitleScreen.setOnClickListener {
//            recreate()
//        }
//    }
}