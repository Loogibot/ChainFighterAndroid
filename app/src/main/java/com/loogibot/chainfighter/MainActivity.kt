package com.loogibot.chainfighter

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
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
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tBinding = TitleWindowBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tBinding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.nutty)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        tBinding.startGame.setOnClickListener {
            mediaPlayer.stop()
            setContentView(binding.root)
            gameStart()
            Players.pChain = Chain()
            Players.oChain = Chain()
        } // starts game
    }

    private fun gameStart() {

        mediaPlayer = MediaPlayer.create(this, R.raw.sixperiment)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        if (Players.turnManager == 0) {
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }
        // button operation
        binding.moveButtonView.kickButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, m.kickSound)
            mediaPlayer.start()
            addMoveToChain(m.kick)
        }
        binding.moveButtonView.punchButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, m.punchSound)
            mediaPlayer.start()
            addMoveToChain(m.punch)
        }
        binding.moveButtonView.grabButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, m.grabSound)
            mediaPlayer.start()
            addMoveToChain(m.grab)
        }
        binding.moveButtonView.dodgeButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, m.dodgeSound)
            mediaPlayer.start()
            addMoveToChain(m.dodge)
        }
        binding.moveButtonView.shieldButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, m.shieldSound)
            mediaPlayer.start()
            addMoveToChain(m.shield)
        }
    }

    private fun addMoveToChain(mv: Move) {

        Players.pChain.chainManager(mv)
        Players.oChain.chainManager(randomMove())

        when (7 - Players.pChain.chainCost) {
            m.kick.cost -> binding.moveButtonView.kickButton.visibility = View.GONE
            m.punch.cost -> binding.moveButtonView.punchButton.visibility = View.GONE
            m.grab.cost -> binding.moveButtonView.grabButton.visibility = View.GONE
        }

        when (Players.pChain.chainList.size) {
            3 -> binding.moveButtonView.root.visibility = View.GONE
        }

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
            binding.moveResults.finalResult,//27
            binding.moveButtonView
        )

        moveResult(drawMoves(Players.pChain, Players.oChain, uIObjectsList))
    }

    private fun moveResult(status: MoveResult.Results.ChainResult) {

        object : CountDownTimer(1000, 200) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                // pause
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                if (Players.opponentHealth <= 0) {
                    gameEnd(status)
                }
                if (Players.playerHealth <= 0) {
                    gameEnd(status)
                }
            }
        }.start()
    }

    private fun gameEnd(final: MoveResult.Results.ChainResult) {
        val eBinding: EndGameBinding = EndGameBinding.inflate(layoutInflater)
        setContentView(eBinding.root)
        mediaPlayer.isLooping = false
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(this, R.raw.maxed_in)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        when (final) {
            MoveResult.playerWin -> eBinding.finalResult.text = getString(R.string.you_won)
            MoveResult.opponentWin -> eBinding.finalResult.text = getString(R.string.opponent_won)
        }
        eBinding.toTitlescreen.setOnClickListener {
            recreate()
            mediaPlayer.stop()
            Players.playerHealth = 200
            Players.opponentHealth = 200
        }
    }
}