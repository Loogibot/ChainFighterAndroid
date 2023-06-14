package com.loogibot.chainfighter

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.loogibot.chainfighter.databinding.ActivityMainBinding
import com.loogibot.chainfighter.databinding.EndGameBinding
import com.loogibot.chainfighter.databinding.TitleWindowBinding
import com.loogibot.chainfighter.gamestate.MoveResult
import com.loogibot.chainfighter.gamestate.randomMove
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.MoveSource.M.m
import com.loogibot.chainfighter.player.Chain
import com.loogibot.chainfighter.player.Players
import com.loogibot.chainfighter.ui.drawMoves

open class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tBinding: TitleWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tBinding = TitleWindowBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tBinding.root)

        tBinding.startGame.setOnClickListener {
            setContentView(binding.root)
            gameStart()
            Players.pChain = Chain()
            Players.oChain = Chain()
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

        Players.pChain.chainManager(mv)
        Players.oChain.chainManager(randomMove())

        when (Players.pChain.chainCost) {
            in 4..5 -> binding.moveButtonView.kickButton.visibility = View.GONE
            in 4..6 -> binding.moveButtonView.punchButton.visibility = View.GONE
            in 4..7 -> binding.moveButtonView.grabButton.visibility = View.GONE
            in 0..3 -> makeAllMovesVisible()
        }
        Log.v(ContentValues.TAG, "##############################################")
        Log.v(ContentValues.TAG, "${Players.pChain.chainCost} is the player's chain cost")
        Log.v(ContentValues.TAG, "##############################################")

        // pass around elements from MainActivity
        val uIObjectsList: List<Any> = listOf(
            binding.moveResults,
            binding.opponentView,

            binding.playerView.playerHPBar,
            binding.opponentView.opponentHPBar,

            binding.playerView.playerHPText,//4
            binding.opponentView.opponentHPText,

            binding.opponentView.OFirstMoveImg,//6
            binding.opponentView.OSecondMoveImg,
            binding.opponentView.OThirdMoveImg,

            binding.opponentView.OFirstMoveTitle,//9
            binding.opponentView.OSecondMoveTitle,
            binding.opponentView.OThirdMoveTitle,

            binding.playerView.PFirstMoveImg,//12
            binding.playerView.PSecondMoveImg,
            binding.playerView.PThirdMoveImg,

            binding.playerView.PFirstMoveTitle,//15
            binding.playerView.PSecondMoveTitle,
            binding.playerView.PThirdMoveTitle,

            binding.moveResults.FirstMoveImgResult,//18
            binding.moveResults.SecondMoveImgResult,
            binding.moveResults.ThirdMoveImgResult,

            binding.moveResults.firstResult,//21
            binding.moveResults.secondResult,
            binding.moveResults.thirdResult,

            getString(R.string.cancel),//24
            binding.playerView.playerChainCost,
            binding.opponentView.opponentChainCost,
            binding.moveResults.finalResult//27
        )

        moveResult(drawMoves(Players.pChain, Players.oChain, uIObjectsList))
    }

    private fun makeAllMovesVisible() {
        binding.moveButtonView.kickButton.visibility = View.VISIBLE
        binding.moveButtonView.punchButton.visibility = View.VISIBLE
        binding.moveButtonView.grabButton.visibility = View.VISIBLE
    }

    private fun moveResult(status: MoveResult.Results.ChainResult) {
        Players.turnManager++

        if (Players.playerHealth <= 0) {
            gameEnd(status)
        }
        if (Players.opponentHealth <= 0) {
            gameEnd(status)
        }
    }

    private fun gameEnd(final: MoveResult.Results.ChainResult) {
        val eBinding: EndGameBinding = EndGameBinding.inflate(layoutInflater)
        setContentView(eBinding.root)

        when (final) {
            MoveResult.playerWin -> eBinding.finalResult.text = getString(R.string.you_won)
            MoveResult.opponentWin -> eBinding.finalResult.text = getString(R.string.opponent_won)
        }
        eBinding.toTitlescreen.setOnClickListener {
            recreate()
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }
    }
}