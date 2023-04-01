package com.loogibot.chainfighter.ui

import android.widget.ImageView
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.gamestate.randomMove
import com.loogibot.chainfighter.player.Chain

// called in gameStart from MainActivity
fun drawMoves(
    playerChain: Chain?,
    uiObj: List<Any>
): String {
    // draws moves when choice is made
    val opponentChoice = randomMove()
    val opponentImage: ImageView = uiObj[6] as ImageView
    val playerImage: ImageView = uiObj[7] as ImageView

    playerImage.setImageResource(playerChoice.moveImg)
    opponentImage.setImageResource(opponentChoice.moveImg)

    return moveCompare(playerChoice, opponentChoice, uiObj)
}