package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.moves.Move
class Chain {

    var firstMove: Move? = null
    var secondMove: Move? = null
    var thirdMove: Move? = null

    val totalDamage = 0

    val chainArray = arrayListOf(firstMove, secondMove, thirdMove)

}