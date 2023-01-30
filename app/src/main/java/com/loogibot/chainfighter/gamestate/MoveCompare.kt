package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.*
import com.loogibot.chainfighter.R
import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.player.Players

fun moveCompare(playerMove: Move, opponentMove: Move) {

    if (playerMove.name != opponentMove.name) {
        // if player is advantageous
        if (playerMove.name in opponentMove.firstAdv || playerMove.name in opponentMove.secondAdv) {
            // note the lambda here is the same syntax as opponent win conditional results

            result = R.string.opponent_damage.toString() + " -" + playerMove.damage

            (opponentMove.name.uppercase() + Players.isWeakToText + playerMove.name.uppercase()).also {
                moveDetail.text = it
            }
            Players.plHPDam += playerMove.damage
            Players.opponentHealth -= playerMove.damage
            (Players.opponentHPLabel + Players.opponentHealth.toString()).also {
                opponentHPlayers.text = it
            }
            opponentHPBar.progress = Players.opponentHealth
        }
        // if opponent is advantageous
        else if (opponentMove.name in playerMove.firstAdv || opponentMove.name in playerMove.secondAdv) {
            "${R.string.player_damage} -${opponentMove.damage}".also {
                result.text = it
            }// who takes dam
            moveDetail.text = buildString {
                append(playerMove.name.uppercase())
                append(Players.isWeakToText)
                append(opponentMove.name.uppercase())
            }
            Players.opHPDam += opponentMove.damage
            Players.playerHealth -= opponentMove.damage
            "$Players.playerHPLabel$Players.playerHealth".also { playerHPlayers.text = it }
            playerHPBar.progress = Players.playerHealth
        }
    } else {
        result.text = R.string.cancel
    }
    moveResult()
}