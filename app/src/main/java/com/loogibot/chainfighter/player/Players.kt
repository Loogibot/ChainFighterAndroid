package com.loogibot.chainfighter.player

open class Players {
    companion object P {
        const val opponent = "OPPONENT"
        const val player = "PLAYER"
        const val opponentHPLabel = "OPPONENT HP IS "
        const val playerHPLabel = "PLAYER HP IS "
        const val isWeakToText = " IS WEAK TO "

        var playerHealth = 0
        var opponentHealth = 0
        var turnManager = 0
    }
}