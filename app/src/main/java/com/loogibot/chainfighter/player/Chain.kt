package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.moves.Move

class Chain {
    var firstMove: Move? = null
    var secondMove: Move? = null
    var thirdMove: Move? = null
    var totalDamage = 0
    var chainArray = arrayListOf<Move>()
}