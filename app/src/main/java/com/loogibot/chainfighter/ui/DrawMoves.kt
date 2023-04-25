package com.loogibot.chainfighter.ui

import android.widget.ImageView
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.gamestate.randomMove
import com.loogibot.chainfighter.player.Chain

// called in gameStart from MainActivity
fun drawMoves(
    playerChain: Chain,
    uiObj: List<Any>
): String {
    // draws moves when choice is made
    val opponentChain = randomMove()

    val oFirstMoveImage: ImageView = uiObj[6] as ImageView
    val oSecondMoveImage: ImageView = uiObj[7] as ImageView
    val oThirdMoveImage: ImageView = uiObj[8] as ImageView

    val pFirstMoveImage: ImageView = uiObj[12] as ImageView
    val pSecondMoveImage: ImageView = uiObj[13] as ImageView
    val pThirdMoveImage: ImageView = uiObj[14] as ImageView

    pFirstMoveImage.setImageResource(playerChain.firstMove!!.moveImg)
    oFirstMoveImage.setImageResource(opponentChain.firstMove!!.moveImg)
    
//    pSecondMoveImage.setImageResource(playerChain.secondMove.moveImg)
//    oSecondMoveImage.setImageResource(opponentChain.secondMove.moveImg)

//    if (opponentChain.secondMove != null) {
//        pThirdMoveImage.setImageResource(playerChain.thirdMove.moveImg)
//        oThirdMoveImage.setImageResource(opponentChain.thirdMove.moveImg)
//    }


    return moveCompare(playerChain, opponentChain, uiObj)
}