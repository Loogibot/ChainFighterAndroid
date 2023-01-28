package com.loogibot.chainfighter.ui

import android.widget.ImageView
import com.loogibot.chainfighter.R
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.gamestate.RandomMove
import com.loogibot.chainfighter.player.Players
import java.util.*

fun drawMoves(playerChoice: Move) {
    // draws moves when choice is made

    val p = Players()
    lateinit var opponentImage: ImageView
    lateinit var playerImage: ImageView

    val opponentChoice = RandomMove()

    opponentImage = findViewById(R.id.opponentChoice)
    playerImage = findViewById(R.id.playerChoice)

    (p.playerHPLabel + p.playerHealth.toString()).also { playerHP.text = it }
    (p.opponentHPLabel + p.opponentHealth.toString()).also { opponentHP.text = it }

    playerImage.setImageResource(playerChoice.moveImg)
    opponentImage.setImageResource(opponentChoice.moveImg)

    "OPPONENT'S MOVE IS ${opponentChoice.name.uppercase(Locale.getDefault())}".also {
        opponentMove.text = it
    }

    moveCompare(playerChoice, opponentChoice)
}