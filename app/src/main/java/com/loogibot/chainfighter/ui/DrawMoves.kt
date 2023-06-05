package com.loogibot.chainfighter.ui

import android.content.ContentValues.TAG
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.loogibot.chainfighter.gamestate.chainCompareResult
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.player.Chain
import com.loogibot.chainfighter.player.Players

// called in gameStart from MainActivity
var results: ArrayList<String> = arrayListOf()
fun drawMoves(
    playerChain: Chain, opponentChain: Chain, uiObj: List<Any>
) {
    // draws moves when choice is made

    // opponent ui set
    val oFirstMoveImage: ImageView = uiObj[6] as ImageView
    val oSecondMoveImage: ImageView = uiObj[7] as ImageView
    val oThirdMoveImage: ImageView = uiObj[8] as ImageView

    val oFirstMoveTextView = uiObj[9] as TextView
    val oSecondMoveTextView = uiObj[10] as TextView
    val oThirdMoveTextView = uiObj[11] as TextView

    // player ui set
    val pFirstMoveImage: ImageView = uiObj[12] as ImageView
    val pSecondMoveImage: ImageView = uiObj[13] as ImageView
    val pThirdMoveImage: ImageView = uiObj[14] as ImageView

    val pFirstMoveTextView = uiObj[15] as TextView
    val pSecondMoveTextView = uiObj[16] as TextView
    val pThirdMoveTextView = uiObj[17] as TextView

    // comparison ui set
    val firstMoveComparisonResult = uiObj[18] as ImageView
    val secondMoveComparisonResult = uiObj[19] as ImageView
    val thirdMoveComparisonResult = uiObj[20] as ImageView

    val firstResultText = uiObj[21] as TextView
    val secondResultText = uiObj[22] as TextView
    val thirdResultText = uiObj[23] as TextView

    val builder = uiObj[0] as AlertDialog.Builder

    if (pFirstMoveImage.drawable == null) {
        firstMoveInChain(
            oFirstMoveImage,
            pFirstMoveImage,
            playerChain,
            opponentChain,
            firstMoveComparisonResult,
            firstResultText,
            pFirstMoveTextView, oFirstMoveTextView
        )
    } else if (pSecondMoveImage.drawable == null) {
        secondMoveInChain(
            oSecondMoveImage,
            pSecondMoveImage,
            playerChain,
            opponentChain,
            secondMoveComparisonResult,
            secondResultText,
            pSecondMoveTextView, oSecondMoveTextView
        )
    } else {
        thirdMoveInChain(
            oThirdMoveImage,
            pThirdMoveImage,
            playerChain,
            opponentChain,
            thirdMoveComparisonResult,
            thirdResultText,
            pThirdMoveTextView, oThirdMoveTextView
        )
        object : CountDownTimer(800, 100) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                // pause
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                moveEnd(builder, uiObj)
            }
        }.start()
    }
}

fun firstMoveInChain(
    oFirstMoveImage: ImageView,
    pFirstMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    firstMoveComparisonResult: ImageView,
    firstResultText: TextView,
    pFirstMoveTextView: TextView,
    oFirstMoveTextView: TextView
) {
    pFirstMoveImage.setImageResource(playerChain.firstMove.moveImg)
    oFirstMoveImage.setImageResource(opponentChain.firstMove.moveImg)
    val firstResult = moveCompare(playerChain.firstMove, opponentChain.firstMove)
    firstResultText.text = firstResult.resultString

    pFirstMoveTextView.text =
        playerChain.firstMove.name + " with " + playerChain.firstMove.cost + " cost, and " + playerChain.firstMove.damage + " damage "

    oFirstMoveTextView.text =
        opponentChain.firstMove.name + " with " + opponentChain.firstMove.cost + " cost, and " + opponentChain.firstMove.damage + " damage "

    results.add(firstResult.resultString)
    firstMoveComparisonResult.setImageResource(firstResult.resultImage)

    Log.v(TAG, "${Players.pChain.chainCost} is the players.chain cost")
    Log.v(TAG, "${playerChain.chainCost} is the playerChain cost")
}

fun secondMoveInChain(
    oSecondMoveImage: ImageView,
    pSecondMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    secondMoveComparisonResult: ImageView,
    secondResultText: TextView,
    pSecondMoveTextView: TextView,
    oSecondMoveTextView: TextView
) {
    pSecondMoveImage.setImageResource(playerChain.secondMove.moveImg)
    oSecondMoveImage.setImageResource(opponentChain.secondMove.moveImg)
    val secondResult = moveCompare(playerChain.secondMove, opponentChain.secondMove)
    secondResultText.text = secondResult.resultString

    pSecondMoveTextView.text =
        playerChain.secondMove.name + " with " + playerChain.secondMove.cost + " cost, and " + playerChain.secondMove.damage + " damage "

    oSecondMoveTextView.text =
        opponentChain.firstMove.name + " with " + opponentChain.firstMove.cost + " cost, and " + opponentChain.firstMove.damage + " damage "

    results.add(secondResult.resultString)
    secondMoveComparisonResult.setImageResource(secondResult.resultImage)

    Log.v(TAG, "${Players.pChain.chainCost} is the players.chain cost")
    Log.v(TAG, "${playerChain.chainCost} is the playerChain cost")
}

fun thirdMoveInChain(
    oThirdMoveImage: ImageView,
    pThirdMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    thirdMoveComparisonResult: ImageView,
    thirdResultText: TextView,
    pThirdMoveTextView: TextView,
    oThirdMoveTextView: TextView
) {
    pThirdMoveImage.setImageResource(playerChain.thirdMove.moveImg)
    oThirdMoveImage.setImageResource(opponentChain.thirdMove.moveImg)
    val thirdResult = moveCompare(playerChain.thirdMove, opponentChain.thirdMove)
    thirdResultText.text = thirdResult.resultString

    pThirdMoveTextView.text =
        playerChain.thirdMove.name + " with " + playerChain.thirdMove.cost + " cost, and " + playerChain.thirdMove.damage + " damage "

    oThirdMoveTextView.text =
        opponentChain.firstMove.name + " with " + opponentChain.firstMove.cost + " cost, and " + opponentChain.firstMove.damage + " damage "

    results.add(thirdResult.resultString)
    thirdMoveComparisonResult.setImageResource(thirdResult.resultImage)
    playerChain.chainCost = 0

    Log.v(TAG, " -------------------------------------------- ")
    Log.v(TAG, "$results are the chain comparison results")
    Log.v(TAG, "${opponentChain.moveSetStr} is the opponent's chain")
    Log.v(TAG, "${playerChain.moveSetStr} is the player's chain")
    Log.v(TAG, "${chainCompareResult(results)} is the final result")
    Log.v(TAG, "${Players.pChain.chainCost} is the players.chain cost")
    Log.v(TAG, "${playerChain.chainCost} is the playerChain cost")
    Log.v(TAG, " -------------------------------------------- ")

    playerChain.chainCost = 0
    opponentChain.chainCost = 0
}

fun moveEnd(builder: AlertDialog.Builder, uiObj: List<Any>) {

//    builder.setView(R.layout.fragment_result)
    builder.setMessage(chainCompareResult(results))
    val alertDialog: AlertDialog = builder.create()
    // Set other dialog properties

    object : CountDownTimer(800, 100) {

        // Callback function, fired on regular interval
        override fun onTick(millisUntilFinished: Long) {
            alertDialog.setCancelable(true)
            alertDialog.show()
        }

        // Callback function, fired when the time is up
        override fun onFinish() {
            alertDialog.cancel()
            clearAllMovesAndResults(uiObj)
        }
    }.start()
}

fun clearAllMovesAndResults(uiObj: List<Any>) {

    val oFirstMoveImage: ImageView = uiObj[6] as ImageView
    val oSecondMoveImage: ImageView = uiObj[7] as ImageView
    val oThirdMoveImage: ImageView = uiObj[8] as ImageView

    val oFirstMoveTextView = uiObj[9] as TextView
    val oSecondMoveTextView = uiObj[10] as TextView
    val oThirdMoveTextView = uiObj[11] as TextView

    val pFirstMoveImage: ImageView = uiObj[12] as ImageView
    val pSecondMoveImage: ImageView = uiObj[13] as ImageView
    val pThirdMoveImage: ImageView = uiObj[14] as ImageView

    val pFirstMoveTextView = uiObj[15] as TextView
    val pSecondMoveTextView = uiObj[16] as TextView
    val pThirdMoveTextView = uiObj[17] as TextView

    val firstMoveComparisonResult = uiObj[18] as ImageView
    val secondMoveComparisonResult = uiObj[19] as ImageView
    val thirdMoveComparisonResult = uiObj[20] as ImageView

    val firstResultText = uiObj[21] as TextView
    val secondResultText = uiObj[22] as TextView
    val thirdResultText = uiObj[23] as TextView

    oFirstMoveImage.setImageResource(0)
    oSecondMoveImage.setImageResource(0)
    oThirdMoveImage.setImageResource(0)
    pFirstMoveImage.setImageResource(0)
    pSecondMoveImage.setImageResource(0)
    pThirdMoveImage.setImageResource(0)
    firstMoveComparisonResult.setImageResource(0)
    secondMoveComparisonResult.setImageResource(0)
    thirdMoveComparisonResult.setImageResource(0)
    firstResultText.text = ""
    secondResultText.text = ""
    thirdResultText.text = ""

    oFirstMoveTextView.text = "Opponent's First Move"
    oSecondMoveTextView.text = "Opponent's Second Move"
    oThirdMoveTextView.text = "Opponent's Third Move"

    pFirstMoveTextView.text = "Player's First Move"
    pSecondMoveTextView.text = "Player's Second Move"
    pThirdMoveTextView.text = "Player's Third Move"

    Players.pChain.chainList.clear()
    Players.oChain.chainList.clear()

    Players.pChain.moveSetStr = ""
    Players.oChain.moveSetStr = ""

    results.clear()
}