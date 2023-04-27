package com.loogibot.chainfighter.player

import android.content.ContentValues.TAG
import android.util.Log
import com.loogibot.chainfighter.moves.Move

class Chain {
    lateinit var firstMove: Move
    lateinit var secondMove: Move
    lateinit var thirdMove: Move

    var totalDamage = 0
    var chainList = arrayListOf<Move>()
    // var chainList = arrayListOf<Move>(MoveSource.m.none, MoveSource.m.none, MoveSource.m.none)

    fun chainManager(m: Move) {
        chainList.add(m)
        firstMove = chainList[0]
        secondMove = chainList[chainList.lastIndex]
        thirdMove = chainList[chainList.lastIndex]

        Log.v(TAG, firstMove.name + " is the first move in the chain")
        Log.v(TAG, secondMove.name + " is the second move in the chain")
        Log.v(TAG, thirdMove.name + " is the third move in the chain")
        Log.v(TAG, chainList.size.toString() + " is the size of the chain")
        Log.v(TAG, chainList.toString() + " is the chain")

        // Log.v(TAG, secondMove.name + " is the chain size")
        // Log.v(TAG, thirdMove.name + " is the chain size")
    }
}