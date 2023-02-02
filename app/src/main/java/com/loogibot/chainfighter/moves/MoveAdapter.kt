package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R.string.*
import com.loogibot.chainfighter.R

open class MoveSource {
    companion object M {
        val m = MoveData()
    }

    val kickStr = kick.toString()
    val grabStr = R.string.grab.toString()
    val dodgeStr = R.string.dodge.toString()
    val shieldStr = R.string.shield.toString()
    val punchStr = R.string.punch.toString()

    val kickImg = R.drawable.player_kick
    val grabImg = R.drawable.player_grab
    val dodgeImg = R.drawable.player_dodge
    val shieldImg = R.drawable.player_shield
    val punchImg = R.drawable.player_punch
}