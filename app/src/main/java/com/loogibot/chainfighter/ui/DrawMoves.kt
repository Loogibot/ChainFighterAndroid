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

    val firstMoveComparisonResult = uiObj[18] as ImageView
    val secondMoveComparisonResult = uiObj[19] as ImageView
    val thirdMoveComparisonResult = uiObj[20] as ImageView

    if (pFirstMoveImage.drawable == null) {
        firstMoveInChain(
            oFirstMoveImage,
            pFirstMoveImage,
            playerChain,
            opponentChain,
            firstMoveComparisonResult
        )
    } else if (pSecondMoveImage.drawable == null) {
        secondMoveInChain(
            oSecondMoveImage,
            pSecondMoveImage,
            playerChain,
            opponentChain,
            secondMoveComparisonResult
        )
    } else {
        thirdMoveInChain(
            oThirdMoveImage,
            pThirdMoveImage,
            playerChain,
            opponentChain,
            thirdMoveComparisonResult
        )
    }
}
fun firstMoveInChain(
    oFirstMoveImage: ImageView,
    pFirstMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    firstMoveComparisonResult: ImageView
) {
    pFirstMoveImage.setImageResource(playerChain.firstMove.moveImg)
    oFirstMoveImage.setImageResource(opponentChain.firstMove.moveImg)

    firstMoveComparisonResult.setImageResource(
        moveCompare(
            playerChain.firstMove,
            opponentChain.firstMove
        )
    )
}

fun secondMoveInChain(
    oSecondMoveImage: ImageView,
    pSecondMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    secondMoveComparisonResult: ImageView
) {
    pSecondMoveImage.setImageResource(playerChain.secondMove.moveImg)
    oSecondMoveImage.setImageResource(opponentChain.secondMove.moveImg)

    secondMoveComparisonResult.setImageResource(
        moveCompare(
            playerChain.secondMove,
            opponentChain.secondMove
        )
    )
}

fun thirdMoveInChain(
    oThirdMoveImage: ImageView,
    pThirdMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    thirdMoveComparisonResult: ImageView
) {
    pThirdMoveImage.setImageResource(playerChain.thirdMove.moveImg)
    oThirdMoveImage.setImageResource(opponentChain.thirdMove.moveImg)

    thirdMoveComparisonResult.setImageResource(
        moveCompare(
            playerChain.thirdMove,
            opponentChain.thirdMove
        )
    )

//    Log.v(TAG, "${} are the chain comparison results")

    Log.v(TAG, "${opponentChain.moveSetStr} is the opponent's chain")
    Log.v(TAG, "${playerChain.moveSetStr} is the player's chain")
}