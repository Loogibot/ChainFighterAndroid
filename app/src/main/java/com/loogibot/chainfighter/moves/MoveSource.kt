package com.loogibot.chainfighter.moves

import com.loogibot.chainfighter.R.string.*
import com.loogibot.chainfighter.R.drawable.*

open class MoveSource {

    companion object M {
        val m = MoveData()
    }

    val punchStr = punch.toString()
    val kickStr = kick.toString()
    val grabStr = grab.toString()
    val dodgeStr = dodge.toString()
    val shieldStr = shield.toString()

    val punchImg = punch_image
    val kickImg = kick_image
    val grabImg = grab_image
    val dodgeImg = dodge_image
    val shieldImg = shield_image
}