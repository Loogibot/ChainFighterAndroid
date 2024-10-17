package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.m
import com.loogibot.chainfighter.player.Players

fun randomMove(): Move {
    val allMoves = listOf(m.kick, m.punch, m.grab, m.dodge, m.shield)

    return when (Players.oChain.chainCost) {
        in 4..5 -> allMoves.subList(1, 4).random()
        in 4..6 -> allMoves.subList(2, 4).random()
        in 4..7 -> allMoves.subList(3, 4).random()
        else -> allMoves.random()
    }
}