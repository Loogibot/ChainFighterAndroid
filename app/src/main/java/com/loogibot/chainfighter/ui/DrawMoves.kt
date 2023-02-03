package com.loogibot.chainfighter.ui

import android.widget.ImageView
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.gamestate.randomMove

// called in gameStart from MainActivity
fun drawMoves(
    playerChoice: Move,
    uiObj: List<Any>
) {
    // draws moves when choice is made
    val opponentChoice = randomMove()
    val opponentImage:ImageView = uiObj[6] as ImageView
    val playerImage:ImageView = uiObj[7] as ImageView

    playerImage.setImageResource(playerChoice.moveImg)
    opponentImage.setImageResource(opponentChoice.moveImg)

    moveCompare(playerChoice, opponentChoice, uiObj)
}