package com.loogibot.chainfighter.player

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.moves.MoveSource

class Chain {

    var chainPosition = 0
    var totalDamage = 0
    var chainList = arrayListOf<Move>(MoveSource.m.none, MoveSource.m.none, MoveSource.m.none)

    var firstMove = chainList[0]
    var secondMove = chainList[1]
    var thirdMove = chainList[2]

}