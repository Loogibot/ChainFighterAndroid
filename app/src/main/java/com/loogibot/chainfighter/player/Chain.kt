package com.loogibot.chainfighter.player

import android.content.ContentValues.TAG
import android.util.Log
import com.loogibot.chainfighter.moves.Move

class Chain {
    lateinit var firstMove: Move
    lateinit var secondMove: Move
    lateinit var thirdMove: Move

    var chainPosition = 0
    var totalDamage = 0
    var chainList = mutableListOf<Move>()

    fun chainManager(m: Move) {
        chainList.add(chainPosition, m)

        firstMove = chainList.first()
        secondMove =
            chainList[if (chainList.lastIndex == 2) 1 else chainList.lastIndex]
        thirdMove = chainList[chainList.lastIndex]

        Log.v(TAG, firstMove.name + " is the first move in the chain")
        Log.v(TAG, secondMove.name + " is the second move in the chain")
        Log.v(TAG, thirdMove.name + " is the third move in the chain")

        Log.v(TAG, chainList.size.toString() + " is the size of the chain")
        Log.v(TAG, chainList.lastIndex.toString() + " is the last index of the chain")
        Log.v(TAG, chainPosition.toString() + " is the position of the chain")

        Log.v(TAG, chainList.toString() + " is the chain")

    }
}