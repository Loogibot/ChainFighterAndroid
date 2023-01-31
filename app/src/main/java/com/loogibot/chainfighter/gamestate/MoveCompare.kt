package com.loogibot.chainfighter.gamestate


import android.widget.ProgressBar
import android.widget.TextView
import com.loogibot.chainfighter.MainActivity
import com.loogibot.chainfighter.R.string.*
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.player.Players

fun moveCompare(playerMove: Move, opponentMove: Move, uiObj: List<Any>) {

    val result = uiObj[0] as TextView
    val moveDetail = uiObj[1] as TextView
    val playerHPBar = uiObj[2] as ProgressBar
    val opponentHPBar = uiObj[3] as ProgressBar
    val playerHP = uiObj[5] as TextView
    val opponentHP = uiObj[6] as TextView

    if (playerMove.name != opponentMove.name) {
        // if player is advantageous
        if (playerMove.name in opponentMove.firstAdv || playerMove.name in opponentMove.secondAdv) {
            // note the lambda here is the same syntax as opponent win conditional results

            result.text = opponent_damage.toString() + " - " + playerMove.damage

            (opponentMove.name.uppercase() + Players.isWeakToText + playerMove.name.uppercase()).also {
                moveDetail.text = it
            }
            Players.plHPDam += playerMove.damage
            Players.opponentHealth -= playerMove.damage
            (Players.opponentHPLabel + Players.opponentHealth.toString()).also {
                opponentHP.text = it
            }
            opponentHPBar.progress = Players.opponentHealth
        }
        // if opponent is advantageous
        else if (opponentMove.name in playerMove.firstAdv || opponentMove.name in playerMove.secondAdv) {
            "$player_damage -${opponentMove.damage}".also {
                result.text = it
            }// who takes dam
            moveDetail.text = buildString {
                append(playerMove.name.uppercase())
                append(Players.isWeakToText)
                append(opponentMove.name.uppercase())
            }
            Players.opHPDam += opponentMove.damage
            Players.playerHealth -= opponentMove.damage
            "$Players.playerHPLabel$Players.playerHealth".also {
                playerHP.text = it
            }
            playerHPBar.progress = Players.playerHealth
        }
    } else {
        result.text = cancel.toString()
    }
    MainActivity()::moveResult.run { result }
}