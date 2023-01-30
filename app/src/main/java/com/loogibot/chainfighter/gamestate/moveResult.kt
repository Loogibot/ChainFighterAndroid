package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.MainActivity
import com.loogibot.chainfighter.R
import com.loogibot.chainfighter.player.Players

fun moveResult() {
    Players.turnManager++

    while (Players.playerHealth > 0 || Players.opponentHealth > 0) run {
        MainActivity::gameStart
    }
    if (Players.playerHealth <= 0) {
        result.text = R.string.plHP0
        MainActivity::gameEnd(Players.opponent)
    }
    if (Players.opponentHealth <= 0) {
        result.text = R.string.opHP0
        MainActivity::gameEnd(Players.player)
    }
}