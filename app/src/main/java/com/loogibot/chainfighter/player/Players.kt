package com.loogibot.chainfighter.player

open class Players {
    companion object P {
        const val opponent = "OPPONENT"
        const val player = "PLAYER"
        const val opponentHPLabel = "OPPONENT HP IS "
        const val playerHPLabel = "PLAYER HP IS "
        const val isWeakToText = " IS WEAK TO "

        var pChain: Chain? = null
        var oChain: Chain? = null

        var playerHealth = 200
        var opponentHealth = 200
        var turnManager = 0
    }
}