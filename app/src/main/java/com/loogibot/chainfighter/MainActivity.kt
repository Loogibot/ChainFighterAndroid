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
        if (Players.turnManager == 0) {
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }

        // button operation
        binding.moveButtonView.kickButton.setOnClickListener {
            addMoveToChain(m.kick)
        }
        binding.moveButtonView.punchButton.setOnClickListener {
            addMoveToChain(m.punch)
        }
        binding.moveButtonView.grabButton.setOnClickListener {
            addMoveToChain(m.grab)
        }
        binding.moveButtonView.dodgeButton.setOnClickListener {
            addMoveToChain(m.dodge)
        }
        binding.moveButtonView.shieldButton.setOnClickListener {
            addMoveToChain(m.shield)
        }
    }

    private fun addMoveToChain(mv: Move) {

        Players.pChain = Chain()
        Players.oChain = Chain()
        // pass around elements from MainActivity
        Players.pChain.chainArray.add(mv)

        val uIObjectsList: List<Any> = listOf(
            binding.playerView.moveResult,
            binding.opponentView.moveDetails,

            binding.playerView.playerHPBar,
            binding.opponentView.opponentHPBar,

            binding.playerView.plyrHpLabel,
            binding.opponentView.oppHpLabel,

            binding.opponentView.OFirstMoveImg,//6
            binding.opponentView.OSecondMoveImg,
            binding.opponentView.OSecondMoveImg,
            binding.opponentView.OFirstMoveTitle,
            binding.opponentView.OSecondMoveTitle,
            binding.opponentView.OThirdMoveTitle,

            binding.playerView.PFirstMoveImg,//12
            binding.playerView.PSecondMoveImg,
            binding.playerView.PThirdMoveImg,
            binding.playerView.PFirstMoveTitle,
            binding.playerView.PSecondMoveTitle,
            binding.playerView.PThirdMoveTitle,

            getString(R.string.cancel)//18
        )

        drawMoves(Players.pChain, uIObjectsList)

//        when (Players.pChain.thirdMove != null) {
//            true -> moveResult(drawMoves(Players.pChain, uIObjectsList))
//            else -> {}
//        }
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