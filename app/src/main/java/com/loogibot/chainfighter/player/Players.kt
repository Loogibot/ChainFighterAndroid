package com.loogibot.chainfighter.player

open class Players {
    companion object P {
        lateinit var pChain: Chain
        lateinit var oChain: Chain

        var playerHealth = 200
        var opponentHealth = 200
        var turnManager = 0
    }
}