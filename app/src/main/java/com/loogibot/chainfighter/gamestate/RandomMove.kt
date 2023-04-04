package com.loogibot.chainfighter.gamestate


import com.loogibot.chainfighter.moves.MoveSource.M.m
import com.loogibot.chainfighter.player.Chain
import com.loogibot.chainfighter.player.Players

fun randomMove(): Chain {
    val allMoves = listOf(m.kick, m.grab, m.dodge, m.shield, m.punch)

    Players.oChain.firstMove = allMoves.random()
    Players.oChain.secondMove = allMoves.random()
    Players.oChain.thirdMove = allMoves.random()

    return Players.oChain
}