package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.R

// val p = Players()
open class Players {
    companion object P {
        const val opponent = R.string.opponent.toString()
        const val player = R.string.player.toString()
        const val opponentHPLabel = "OPPONENT HP IS "
        const val playerHPLabel = "PLAYER HP IS "
        const val isWeakToText = " IS WEAK TO "

        var plHPDam = 0
        var opHPDam = 0
        var playerHealth = 200
        var opponentHealth = 200
        var turnManager = 0
    }
}