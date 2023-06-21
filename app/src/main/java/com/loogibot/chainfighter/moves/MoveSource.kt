package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R.drawable.dodge_image
import com.loogibot.chainfighter.R.drawable.grab_image
import com.loogibot.chainfighter.R.drawable.kick_image
import com.loogibot.chainfighter.R.drawable.punch_image
import com.loogibot.chainfighter.R.drawable.shield_image
import com.loogibot.chainfighter.R.raw.fx_dodge
import com.loogibot.chainfighter.R.raw.fx_grab
import com.loogibot.chainfighter.R.raw.fx_kick
import com.loogibot.chainfighter.R.raw.fx_punch
import com.loogibot.chainfighter.R.raw.fx_shield


open class MoveSource {
    companion object M {
        val m = MoveData()
    }

    val kickStr = "KICK"
    val punchStr = "PUNCH"
    val grabStr = "GRAB"
    val dodgeStr = "DODGE"
    val shieldStr = "SHIELD"

    val kickImg = kick_image
    val punchImg = punch_image
    val grabImg = grab_image
    val dodgeImg = dodge_image
    val shieldImg = shield_image

    val punchSound = fx_punch
    val kickSound = fx_kick
    val grabSound = fx_grab
    val dodgeSound = fx_dodge
    val shieldSound = fx_shield
}