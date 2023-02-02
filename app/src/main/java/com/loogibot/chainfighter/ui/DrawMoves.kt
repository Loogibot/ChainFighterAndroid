package com.loogibot.chainfighter.ui

import android.widget.ImageView
import android.widget.TextView
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.gamestate.randomMove
import com.loogibot.chainfighter.player.Players

// called in gameStart from MainActivity
fun drawMoves(
    playerChoice: Move,
    uiObj: List<Any>
) {
    // draws moves when choice is made
    val opponentChoice = randomMove()
    val playerImage:ImageView = uiObj[8] as ImageView
    val opponentImage:ImageView = uiObj[9] as ImageView
    // val opponentMove:TextView = uiObj[7] as TextView

    Players.playerHPLabel + Players.playerHealth
    Players.opponentHPLabel + Players.opponentHealth

    playerImage.setImageResource(playerChoice.moveImg)
    opponentImage.setImageResource(opponentChoice.moveImg)

    moveCompare(playerChoice, opponentChoice, uiObj)
}