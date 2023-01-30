package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.m
import com.loogibot.chainfighter.player.Players

fun randomMove(): Move {
    val allMoves = listOf(m.kick, m.grab, m.dodge, m.shield, m.punch)
    return when (Players.player) {
        Players.opponent -> allMoves.random()
        else -> allMoves.random()
    }
}