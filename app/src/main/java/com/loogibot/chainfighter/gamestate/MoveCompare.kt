package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.ui.MoveResult.Results.cancelResult
import com.loogibot.chainfighter.ui.MoveResult.Results.neutralResult
import com.loogibot.chainfighter.ui.MoveResult.Results.opponentMoveWinResult
import com.loogibot.chainfighter.ui.MoveResult.Results.playerMoveWinResult
fun moveCompare(playerMove: Move, opponentMove: Move): Int {

//    val result = uiObj[0] as TextView
//    val moveDetail = uiObj[1] as TextView

//    val playerHPBar = uiObj[2] as ProgressBar
//    val opponentHPBar = uiObj[3] as ProgressBar
//    val playerHP = uiObj[4] as TextView
//    val opponentHP = uiObj[5] as TextView

//    val cancel = uiObj.last() as String

//    var winner = "NO ONE YET"
//    var i = 0
    var result = neutralResult

    if (playerMove != opponentMove) {
        //player move is successful
        if (playerMove.name == opponentMove.firstAdv || playerMove.name == opponentMove.secondAdv) {
            // result will display who, in this case opponent, took how much damage
            result = playerMoveWinResult
            // move detail describes which move is weak to another or cancel in case of a draw
//            moveDetail.text =
//                "${opponentChain.chainList[i]!!.name} + ${Players.isWeakToText} + ${it.name}"
//            // damage is applied
//            Players.opponentHealth -= it.damage
//            opponentHP.text = "${Players.opponentHPLabel} + ${Players.opponentHealth}"
//            opponentHPBar.progress = Players.opponentHealth

            //update results image

        } else if (opponentMove.name == playerMove.firstAdv || opponentMove.name == playerMove.secondAdv) {
            // result will display who, in this case opponent, took how much damage
            result = opponentMoveWinResult
//            // move detail describes which move is weak to another or cancel in case of a draw
//            moveDetail.text =
//                "${it.name} + ${Players.isWeakToText} + ${opponentChain.chainList[i]!!.name}"
//            // damage is applied
//            Players.playerHealth -= opponentChain.chainList[i]!!.damage
//            playerHP.text = "${Players.playerHPLabel} + ${Players.playerHealth}"
//            playerHPBar.progress = Players.playerHealth
            //update results image

        }
    } else {
        return cancelResult
    }
    return result
}