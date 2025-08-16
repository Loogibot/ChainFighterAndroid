package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R
import com.loogibot.chainfighter.R.raw.fx_dodge
import com.loogibot.chainfighter.R.raw.fx_grab
import com.loogibot.chainfighter.R.raw.fx_kick
import com.loogibot.chainfighter.R.raw.fx_punch
import com.loogibot.chainfighter.R.raw.fx_shield


open class MoveSource {
    val kickStr = R.string.kick.toString()
    val grabStr = R.string.grab.toString()
    val dodgeStr = R.string.dodge.toString()
    val shieldStr = R.string.shield.toString()
    val punchStr = R.string.punch.toString()

    val kickImg = R.drawable.player_kick
    val grabImg = R.drawable.player_grab
    val dodgeImg = R.drawable.player_dodge
    val shieldImg = R.drawable.player_shield
    val punchImg = R.drawable.player_punch

    val punchSound = fx_punch
    val kickSound = fx_kick
    val grabSound = fx_grab
    val dodgeSound = fx_dodge
    val shieldSound = fx_shield
}