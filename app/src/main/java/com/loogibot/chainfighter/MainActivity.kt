package com.loogibot.chainfighter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.databinding.ActivityMainBinding
import com.loogibot.chainfighter.databinding.EndgamepageBinding
import com.loogibot.chainfighter.databinding.TitlewindowBinding
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.MoveSource.M.m
import com.loogibot.chainfighter.player.Chain
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

            binding.opponentView.OFirstMoveImg,
            binding.opponentView.OSecondMoveImg,
            binding.opponentView.OSecondMoveImg,
            binding.opponentView.OFirstMoveTitle,
            binding.opponentView.OSecondMoveTitle,
            binding.opponentView.OThirdMoveTitle,

            binding.playerView.PFirstMoveImg,
            binding.playerView.PSecondMoveImg,
            binding.playerView.PThirdMoveImg,
            binding.playerView.PFirstMoveTitle,
            binding.playerView.PSecondMoveTitle,
            binding.playerView.PThirdMoveTitle,
            getString(R.string.cancel)
        )

        if (Players.turnManager == 0) {
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }
        // create chain for player


        // button operation
        binding.moveButtonView.kickButton.setOnClickListener {
            drawMoves(m.kick, uIObjectsList)
            moveResult(drawMoves(m.kick, uIObjectsList))
        }
        binding.moveButtonView.punchButton.setOnClickListener {
            drawMoves(m.punch, uIObjectsList)
            moveResult(drawMoves(m.punch, uIObjectsList))
        }
        binding.moveButtonView.grabButton.setOnClickListener {
            drawMoves(m.grab, uIObjectsList)
            moveResult(drawMoves(m.grab, uIObjectsList))
        }
        binding.moveButtonView.dodgeButton.setOnClickListener {
            drawMoves(m.dodge, uIObjectsList)
            moveResult(drawMoves(m.dodge, uIObjectsList))
        }
        binding.moveButtonView.shieldButton.setOnClickListener {
            drawMoves(m.shield, uIObjectsList)
            moveResult(drawMoves(m.shield, uIObjectsList))
        }
    }

    private fun addMoveToChain(m: Move) {

        if (Players.pChain.firstMove == null) {
            Players.pChain.firstMove = m
        } else if (Players.pChain.secondMove == null) {
            Players.pChain.secondMove = m
        } else {
            Players.pChain.thirdMove = m
        }

        

    }

    private fun moveResult(status: String) {

        Players.turnManager++

        if (Players.playerHealth <= 0) {
            binding.playerView.moveResult.text = R.string.plHP0.toString()
            gameEnd(status)
        }
        if (Players.opponentHealth <= 0) {
            binding.playerView.moveResult.text = R.string.opHP0.toString()
            gameEnd(status)
        }
    }

    private fun gameEnd(final: String) {
        val eBinding: EndgamepageBinding = EndgamepageBinding.inflate(layoutInflater)
        setContentView(eBinding.root)

        when (final) {
            Players.player -> eBinding.finalResult.text = getString(R.string.you_won)
            Players.opponent -> eBinding.finalResult.text = getString(R.string.opponent_won)
        }
        eBinding.toTitlescreen.setOnClickListener {
            recreate()
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }
    }
}