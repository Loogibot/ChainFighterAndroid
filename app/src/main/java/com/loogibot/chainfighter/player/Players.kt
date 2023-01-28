package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.R

open class Players {

    val opponent = R.string.opponent.toString()
    val player = R.string.player.toString()
    val opponentHPLabel = "OPPONENT HP IS "
    val playerHPLabel = "PLAYER HP IS "
    val isWeakToText = " IS WEAK TO "

    var plHPDam = 0
    var opHPDam = 0
    var playerHealth = 200
    var opponentHealth = 200
    var turnManager = 0
}