package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.m
import com.loogibot.chainfighter.player.Players

fun RandomMove(): Move {

    val p = Players()

    val allMoves = listOf(m.kick, m.grab, m.dodge, m.shield, m.punch)
    return when (p.player) {
        p.opponent -> allMoves.random()
        else -> allMoves.random()
    }
}