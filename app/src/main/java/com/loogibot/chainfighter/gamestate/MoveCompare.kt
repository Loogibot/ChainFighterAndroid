package com.loogibot.chainfighter.gamestate

import android.widget.ProgressBar
import android.widget.TextView
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
    var winner = "NO ONE YET"
    var i = 0

    playerChain.chainArray.forEach {
        if (it != opponentChain.chainArray[i]) {
            if (it!!.name == opponentChain.chainArray[i]!!.firstAdv || it.name == opponentChain.chainArray[i]!!.secondAdv) {
                // result will display who, in this case opponent, took how much damage
                result.text = "${Players.opponent} TOOK ${playerChain.totalDamage} DAMAGE!"
                // move detail describes which move is weak to another or cancel in case of a draw
                moveDetail.text = "${opponentChain.chainArray[i]!!.name} + ${Players.isWeakToText} + ${it.name}"
                // damage is applied
                Players.opponentHealth -= it.damage
                opponentHP.text = "${Players.opponentHPLabel} + ${Players.opponentHealth}"
                opponentHPBar.progress = Players.opponentHealth

            } else if (opponentChain.chainArray[i]!!.name == it.firstAdv || opponentChain.chainArray[i]!!.name == it.secondAdv) {
                // result will display who, in this case opponent, took how much damage
                result.text = "${Players.player} TOOK ${opponentChain.totalDamage} DAMAGE!"
                // move detail describes which move is weak to another or cancel in case of a draw
                moveDetail.text = "${it.name} + ${Players.isWeakToText} + ${opponentChain.chainArray[i]!!.name}"
                // damage is applied
                Players.playerHealth -= opponentChain.chainArray[i]!!.damage
                playerHP.text = "${Players.playerHPLabel} + ${Players.playerHealth}"
                playerHPBar.progress = Players.playerHealth
            }
        }  else {
            result.text = cancel
        }
        i++
    }

    if (Players.opponentHealth <= 0) winner = Players.player
    else if (Players.playerHealth <= 0) winner = Players.opponent
    return winner
}