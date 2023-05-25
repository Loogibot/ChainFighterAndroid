package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.MoveSource.M.m

fun randomMove(): Move {
    val allMoves = listOf(m.kick, m.grab, m.dodge, m.shield, m.punch)
    return allMoves.random()
}