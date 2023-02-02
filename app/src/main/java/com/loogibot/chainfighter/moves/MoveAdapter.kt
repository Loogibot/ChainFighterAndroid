package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R

open class MoveSource {
    companion object M {
        val m = MoveData()
    }

    val kickStr = "KICK"
    val grabStr = "GRAB"
    val dodgeStr = "DODGE"
    val shieldStr = "SHIELD"
    val punchStr = "PUNCH"

    val kickImg = R.drawable.player_kick
    val grabImg = R.drawable.player_grab
    val dodgeImg = R.drawable.player_dodge
    val shieldImg = R.drawable.player_shield
    val punchImg = R.drawable.player_punch
}