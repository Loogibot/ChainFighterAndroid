package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.moves.Move

class Chain {

    var firstMove: Move? = null
    var secondMove: Move? = null
    var thirdMove: Move? = null

    val totalDamage = firstMove?.damage!! + secondMove?.damage!! + thirdMove?.damage!!

    val chainArray = arrayListOf<Move?>(firstMove,secondMove,thirdMove)

}