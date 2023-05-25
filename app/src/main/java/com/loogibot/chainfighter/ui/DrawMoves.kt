package com.loogibot.chainfighter.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ImageView
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.player.Chain

// called in gameStart from MainActivity
fun drawMoves(
    playerChain: Chain,
    opponentChain: Chain,
    uiObj: List<Any>
) {
    // draws moves when choice is made

    val oFirstMoveImage: ImageView = uiObj[6] as ImageView
    val oSecondMoveImage: ImageView = uiObj[7] as ImageView
    val oThirdMoveImage: ImageView = uiObj[8] as ImageView

    val pFirstMoveImage: ImageView = uiObj[12] as ImageView
    val pSecondMoveImage: ImageView = uiObj[13] as ImageView
    val pThirdMoveImage: ImageView = uiObj[14] as ImageView

    if (pFirstMoveImage.drawable == null) {
        firstMoveInChain(oFirstMoveImage, pFirstMoveImage, playerChain, opponentChain)
    } else if (pSecondMoveImage.drawable == null) {
        secondMoveInChain(oSecondMoveImage, pSecondMoveImage, playerChain, opponentChain)
    } else {
        thirdMoveInChain(oThirdMoveImage, pThirdMoveImage, playerChain, opponentChain, uiObj)
    }

}

fun firstMoveInChain(
    oFirstMoveImage: ImageView,
    pFirstMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain
) {
    pFirstMoveImage.setImageResource(playerChain.firstMove.moveImg)
    oFirstMoveImage.setImageResource(opponentChain.firstMove.moveImg)
}

fun secondMoveInChain(
    oSecondMoveImage: ImageView,
    pSecondMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain
) {
    pSecondMoveImage.setImageResource(playerChain.secondMove.moveImg)
    oSecondMoveImage.setImageResource(opponentChain.secondMove.moveImg)
}

fun thirdMoveInChain(
    oThirdMoveImage: ImageView,
    pThirdMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    uiObj: List<Any>
): String {
    pThirdMoveImage.setImageResource(playerChain.thirdMove.moveImg)
    oThirdMoveImage.setImageResource(opponentChain.thirdMove.moveImg)

    Log.v(TAG, "${opponentChain.moveSetStr} is the opponent's chain")
    Log.v(TAG, "${playerChain.moveSetStr} is the player's chain")

    return moveCompare(playerChain, opponentChain, uiObj)
}