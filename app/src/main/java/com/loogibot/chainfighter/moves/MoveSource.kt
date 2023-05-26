package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R.drawable.*

open class MoveSource{
    companion object M {
        val m = MoveData()
    }

    val punchStr = "PUNCH"
    val kickStr = "KICK"
    val grabStr = "GRAB"
    val dodgeStr = "DODGE"
    val shieldStr = "SHIELD"

    val punchImg = punch_image
    val kickImg = kick_image
    val grabImg = grab_image
    val dodgeImg = dodge_image
    val shieldImg = shield_image
}