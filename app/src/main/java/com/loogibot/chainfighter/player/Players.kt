package com.loogibot.chainfighter.player
import com.loogibot.chainfighter.R.string.*
open class Players {
    companion object P {
        const val opponent = _opponent.toString()
        const val player = _player.toString()
        const val opponentHPLabel = "OPPONENT HP IS "
        const val playerHPLabel = "PLAYER HP IS "
        const val isWeakToText = " IS WEAK TO "

        var playerHealth = 0
        var opponentHealth = 0
        var turnManager = 0
    }
}