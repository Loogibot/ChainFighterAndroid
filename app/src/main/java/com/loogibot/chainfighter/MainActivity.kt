package com.loogibot.chainfighter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.databinding.ActivityMainBinding
import com.loogibot.chainfighter.databinding.EndgamepageBinding
import com.loogibot.chainfighter.databinding.TitlewindowBinding
import com.loogibot.chainfighter.moves.MoveSource.M.m
import com.loogibot.chainfighter.player.Players
import com.loogibot.chainfighter.ui.drawMoves

open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val tBinding: TitlewindowBinding = TitlewindowBinding.inflate(layoutInflater)
        setContentView(tBinding.root)
        // switch activity layout for this implementation
        // there's (probably) a better way by making the title window its own activity,
        // but this is fine fow now

        tBinding.startGame.setOnClickListener {
            setContentView(binding.root)
            gameStart()
        } // starts game
    }

    private fun gameStart() {

        // pass around elements from MainActivity
        val uIObjectsList: List<Any> = listOf(
            binding.playerView.moveResult,
            binding.opponentView.moveDetails,
            binding.playerView.playerHPBar,
            binding.opponentView.opponentHPBar,
            binding.playerView.plyrHpLabel,
            binding.opponentView.oppHpLabel,
            binding.opponentView.opponentChoiceImg,
            binding.playerView.playerChoiceImg,
            getString(R.string.cancel)
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

    private fun moveResult() {

        Players.turnManager++

        if (Players.playerHealth <= 0) {
            binding.playerView.moveResult.text = R.string.plHP0.toString()
            gameEnd(Players.opponent)
        }
        if (Players.opponentHealth <= 0) {
            binding.playerView.moveResult.text = R.string.opHP0.toString()
            gameEnd(Players.player)
        }

    }

    private fun gameEnd(final: String) {
        val eBinding: EndgamepageBinding = EndgamepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (final) {
            (Players.player) -> eBinding.finalResult.text = getString(R.string.you_won)
            (Players.opponent) -> eBinding.finalResult.text = getString(R.string.opponent_won)
        }
        eBinding.toTitlescreen.setOnClickListener {
            recreate()
        }
    }
}