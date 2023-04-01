package com.loogibot.chainfighter.moves
// creates template of all possible moves with name, damage and that move's advantages
data class Move(
    val name: String,
    val damage: Int,
    val firstAdv: String,
    val secondAdv: String,
    val moveImg: Int,
    val cost: Int
)