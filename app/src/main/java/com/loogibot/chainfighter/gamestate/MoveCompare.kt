package com.loogibot.chainfighter.gamestate

import android.widget.ProgressBar
import android.widget.TextView
import com.loogibot.chainfighter.R.string.*
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.player.Players

fun moveCompare(playerMove: Move, opponentMove: Move, uiObj: List<Any>) {

    val result = uiObj[0] as TextView
    val moveDetail = uiObj[1] as TextView
    val playerHPBar = uiObj[2] as ProgressBar
    val opponentHPBar = uiObj[3] as ProgressBar
    val playerHP = uiObj[4] as TextView
    val opponentHP = uiObj[5] as TextView

    if (playerMove.name != opponentMove.name) {
        // if player is advantageous
        if (playerMove.name in opponentMove.firstAdv || playerMove.name in opponentMove.secondAdv) {
            // result will display who, in this case opponent, took how much damage
            "${Players.opponent} TOOK ${playerMove.damage} DAMAGE!".also { result.text = it }
            // move detail describes which move is weak to another or cancel in case of a draw
            (opponentMove.name + Players.isWeakToText + playerMove.name).also {
                moveDetail.text = it
            }
            // damage is applied
            Players.opponentHealth -= playerMove.damage
            (Players.opponentHPLabel + Players.opponentHealth.toString()).also {
                opponentHP.text = it
            }
            opponentHPBar.progress = Players.opponentHealth
        }
        // if opponent is advantageous
        else if (opponentMove.name in playerMove.firstAdv || opponentMove.name in playerMove.secondAdv) {
            // result will display who, in this case opponent, took how much damage
            "${Players.player} TOOK ${opponentMove.damage} DAMAGE!".also { result.text = it }
            // move detail describes which move is weak to another or cancel in case of a draw
            (playerMove.name + Players.isWeakToText + opponentMove.name).also {
                moveDetail.text = it
            }
            // damage is applied
            Players.playerHealth -= opponentMove.damage
            (Players.playerHPLabel + Players.playerHealth.toString()).also {
                playerHP.text = it
            }
            playerHPBar.progress = Players.playerHealth
        }
    } else {
        result.text = cancel.toString()
    }

}