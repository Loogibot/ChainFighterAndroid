package com.loogibot.chainfighter.player

import android.content.ContentValues
import android.util.Log
import com.loogibot.chainfighter.moves.Move

class Chain {
    lateinit var firstMove: Move
    lateinit var secondMove: Move
    lateinit var thirdMove: Move

    var chainCost = 0
    var totalDamage = 0
    var chainList = mutableListOf<Move>()
    var moveSetStr = ""

    fun chainManager(m: Move) {
        chainList.add(m)

        firstMove = chainList.first()
        secondMove = chainList[if (chainList.lastIndex == 2) 1 else chainList.lastIndex]
        thirdMove = chainList[chainList.lastIndex]

        moveSetStr += chainList[chainList.lastIndex].name + " "
        chainCost += chainList[chainList.lastIndex].cost

        if (chainList.size == 3) {
            chainCost = chainCostCalc(chainList)
            totalDamage = chainDamageCalc(chainList)
        }

        Log.v(ContentValues.TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        Log.v(
            ContentValues.TAG, "first move ${firstMove.name} deals ${firstMove.damage} damage"
        )
        Log.v(ContentValues.TAG, "second move ${secondMove.name} deals ${secondMove.damage} damage")
        Log.v(ContentValues.TAG, "third move ${thirdMove.name} deals ${thirdMove.damage} damage")
        Log.v(ContentValues.TAG, "$totalDamage is the the chain's total damage")
        Log.v(ContentValues.TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    }

    private fun chainCostCalc(chainList: List<Move>): Int {
        var currentCost = 0
        chainList.forEach {
            currentCost += it.cost
        }
        return currentCost
    }

    private fun chainDamageCalc(chainList: List<Move>): Int {
        var currentTotalDamage = 0
        chainList.forEach {
            currentTotalDamage += it.damage
        }
        return currentTotalDamage
    }
}