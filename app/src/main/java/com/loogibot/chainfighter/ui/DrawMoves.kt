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
    val opponentChain = randomMove()

    val oFirstMove: ImageView = uiObj[6] as ImageView
    val oSecondMove: ImageView = uiObj[7] as ImageView
    val oThirdMove: ImageView = uiObj[8] as ImageView

    val pFirstMoveImage: ImageView = uiObj[12] as ImageView
    val pSecondMoveImage: ImageView = uiObj[13] as ImageView
    val pThirdMoveImage: ImageView = uiObj[14] as ImageView

    pFirstMoveImage.setImageResource(playerChain?.firstMove!!.moveImg)
    pSecondMoveImage.setImageResource(playerChain.secondMove!!.moveImg)
    pThirdMoveImage.setImageResource(playerChain.thirdMove!!.moveImg)

    oFirstMove.setImageResource(opponentChain.firstMove!!.moveImg)
    oSecondMove.setImageResource(opponentChain.secondMove!!.moveImg)
    oThirdMove.setImageResource(opponentChain.thirdMove!!.moveImg)

    return moveCompare(playerChain, opponentChain, uiObj)
}