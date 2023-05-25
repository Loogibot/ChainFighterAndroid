package com.loogibot.chainfighter.gamestate

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.loogibot.chainfighter.R
import com.loogibot.chainfighter.player.Chain
import com.loogibot.chainfighter.player.Players

fun moveCompare(playerChain: Chain, opponentChain: Chain, uiObj: List<Any>): String {

    val result = uiObj[0] as TextView
    val moveDetail = uiObj[1] as TextView
    val playerHPBar = uiObj[2] as ProgressBar
    val opponentHPBar = uiObj[3] as ProgressBar
    val playerHP = uiObj[4] as TextView
    val opponentHP = uiObj[5] as TextView
    val cancel = uiObj.last() as String
    val firstMoveComparisonResult = uiObj[18] as ImageView
    val SecondMoveComparisonResult = uiObj[19] as ImageView
    val ThirdMoveComparisonResult = uiObj[20] as ImageView

    var winner = "NO ONE YET"
    var i = 0

    playerChain.chainList.forEach {
        if (it != opponentChain.chainList[i]) {
            //player move is successful
            if (it.name == opponentChain.chainList[i].firstAdv || it.name == opponentChain.chainList[i].secondAdv) {
                // result will display who, in this case opponent, took how much damage
                result.text = "${Players.opponent} TOOK ${playerChain.totalDamage} DAMAGE!"
                // move detail describes which move is weak to another or cancel in case of a draw
                moveDetail.text =
                    "${opponentChain.chainList[i]!!.name} + ${Players.isWeakToText} + ${it.name}"
                // damage is applied
                Players.opponentHealth -= it.damage
                opponentHP.text = "${Players.opponentHPLabel} + ${Players.opponentHealth}"
                opponentHPBar.progress = Players.opponentHealth

                //update results image
                firstMoveComparisonResult.setImageResource(R.drawable.player_move_win)

            } else if (opponentChain.chainList[i].name == it.firstAdv || opponentChain.chainList[i].name == it.secondAdv) {
                // result will display who, in this case opponent, took how much damage
                result.text = "${Players.player} TOOK ${opponentChain.totalDamage} DAMAGE!"
                // move detail describes which move is weak to another or cancel in case of a draw
                moveDetail.text =
                    "${it.name} + ${Players.isWeakToText} + ${opponentChain.chainList[i]!!.name}"
                // damage is applied
                Players.playerHealth -= opponentChain.chainList[i]!!.damage
                playerHP.text = "${Players.playerHPLabel} + ${Players.playerHealth}"
                playerHPBar.progress = Players.playerHealth
                //update results image
                firstMoveComparisonResult.setImageResource(R.drawable.opponent_move_win)
            }
        } else {
            firstMoveComparisonResult.setImageResource(R.drawable.cancel_image)
            result.text = cancel
        }
        i++
    }

    if (Players.opponentHealth <= 0) winner = Players.player
    else if (Players.playerHealth <= 0) winner = Players.opponent
    return winner
}