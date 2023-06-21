package com.loogibot.chainfighter.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.loogibot.chainfighter.databinding.FinalResultBinding
import com.loogibot.chainfighter.databinding.MoveButtonsBinding
import com.loogibot.chainfighter.databinding.MoveResultsBinding
import com.loogibot.chainfighter.gamestate.MoveResult
import com.loogibot.chainfighter.gamestate.MoveResult.Results.opponentWin
import com.loogibot.chainfighter.gamestate.MoveResult.Results.playerWin
import com.loogibot.chainfighter.gamestate.chainCompareResult
import com.loogibot.chainfighter.gamestate.moveCompare
import com.loogibot.chainfighter.player.Chain
import com.loogibot.chainfighter.player.Players

// called in gameStart from MainActivity
var results: ArrayList<String> = arrayListOf()
fun drawMoves(
    playerChain: Chain, opponentChain: Chain, uiObj: List<Any>
): MoveResult.Results.ChainResult {
    var endResult: MoveResult.Results.ChainResult = MoveResult.neutral
    // chain cost text
    val playerChainCostText = uiObj[25] as TextView
    val opponentChainCostText = uiObj[26] as TextView
    // HP progress bars
    val playerHPBar = uiObj[2] as ProgressBar
    val opponentHPBar = uiObj[3] as ProgressBar

    val playerHPText = uiObj[4] as TextView
    val opponentHPText = uiObj[5] as TextView

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

    //final result
    val finalResult = uiObj[27] as FinalResultBinding
    val moveResults = uiObj[0] as MoveResultsBinding

    if (pFirstMoveImage.drawable == null) {
        firstMoveInChain(
            oFirstMoveImage,
            pFirstMoveImage,
            playerChain,
            opponentChain,
            firstMoveComparisonResult,
            firstResultText,
            pFirstMoveTextView,
            oFirstMoveTextView,
            playerChainCostText,
            opponentChainCostText
        )
    } else if (pSecondMoveImage.drawable == null) {
        secondMoveInChain(
            oSecondMoveImage,
            pSecondMoveImage,
            playerChain,
            opponentChain,
            secondMoveComparisonResult,
            secondResultText,
            pSecondMoveTextView,
            oSecondMoveTextView,
            playerChainCostText,
            opponentChainCostText
        )
    } else {
        endResult =
            thirdMoveInChain(
                oThirdMoveImage,
                pThirdMoveImage,
                playerChain,
                opponentChain,
                thirdMoveComparisonResult,
                thirdResultText,
                pThirdMoveTextView,
                oThirdMoveTextView,
                playerChainCostText,
                opponentChainCostText
            )
        object : CountDownTimer(1000, 200) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                // pause
                "PLAYER HP: ${Players.playerHealth}".also { playerHPText.text = it }
                "OPPONENT HP: ${Players.opponentHealth}".also { opponentHPText.text = it }
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                moveEnd(finalResult, moveResults, uiObj)
                playerHPBar.progress = Players.playerHealth
                opponentHPBar.progress = Players.opponentHealth
            }
        }.start()
        playerChain.chainCost = 0
        opponentChain.chainCost = 0
    }
    return endResult
}

fun firstMoveInChain(
    oFirstMoveImage: ImageView,
    pFirstMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    firstMoveComparisonResult: ImageView,
    firstResultText: TextView,
    pFirstMoveTextView: TextView,
    oFirstMoveTextView: TextView,
    playerChainCostText: TextView,
    opponentChainCostText: TextView
) {
    pFirstMoveImage.setImageResource(playerChain.firstMove.moveImg)
    oFirstMoveImage.setImageResource(opponentChain.firstMove.moveImg)
    val firstResult = moveCompare(playerChain.firstMove, opponentChain.firstMove)
    firstResultText.text = firstResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerChainCostText.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also { opponentChainCostText.text = it }

    "${playerChain.firstMove.name} costs ${playerChain.firstMove.cost}, deals ${playerChain.firstMove.damage} damage ".also {
        pFirstMoveTextView.text = it
    }

    "${opponentChain.firstMove.name} costs ${opponentChain.firstMove.cost}, deals ${opponentChain.firstMove.damage} damage ".also {
        oFirstMoveTextView.text = it
    }

    results.add(firstResult.resultString)
    firstMoveComparisonResult.setImageResource(firstResult.resultImage)
}

fun secondMoveInChain(
    oSecondMoveImage: ImageView,
    pSecondMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    secondMoveComparisonResult: ImageView,
    secondResultText: TextView,
    pSecondMoveTextView: TextView,
    oSecondMoveTextView: TextView,
    playerChainCostText: TextView,
    opponentChainCostText: TextView
) {
    pSecondMoveImage.setImageResource(playerChain.secondMove.moveImg)
    oSecondMoveImage.setImageResource(opponentChain.secondMove.moveImg)
    val secondResult = moveCompare(playerChain.secondMove, opponentChain.secondMove)
    secondResultText.text = secondResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerChainCostText.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also { opponentChainCostText.text = it }

    (playerChain.secondMove.name + " costs " + playerChain.secondMove.cost + ", deals " + playerChain.secondMove.damage + " damage ").also {
        pSecondMoveTextView.text = it
    }

    (opponentChain.secondMove.name + " costs " + opponentChain.secondMove.cost + ", deals " + opponentChain.secondMove.damage + " damage ").also {
        oSecondMoveTextView.text = it
    }

    results.add(secondResult.resultString)
    secondMoveComparisonResult.setImageResource(secondResult.resultImage)
}

fun thirdMoveInChain(
    oThirdMoveImage: ImageView,
    pThirdMoveImage: ImageView,
    playerChain: Chain,
    opponentChain: Chain,
    thirdMoveComparisonResult: ImageView,
    thirdResultText: TextView,
    pThirdMoveTextView: TextView,
    oThirdMoveTextView: TextView,
    playerChainCostText: TextView,
    opponentChainCostText: TextView
): MoveResult.Results.ChainResult {
    pThirdMoveImage.setImageResource(playerChain.thirdMove.moveImg)
    oThirdMoveImage.setImageResource(opponentChain.thirdMove.moveImg)

    val thirdResult = moveCompare(playerChain.thirdMove, opponentChain.thirdMove)
    thirdResultText.text = thirdResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerChainCostText.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also { opponentChainCostText.text = it }

    (playerChain.thirdMove.name + " costs " + playerChain.thirdMove.cost + ", deals " + playerChain.thirdMove.damage + " damage ").also {
        pThirdMoveTextView.text = it
    }

    (opponentChain.thirdMove.name + " costs " + opponentChain.thirdMove.cost + ", deals " + opponentChain.thirdMove.damage + " damage ").also {
        oThirdMoveTextView.text = it
    }

    results.add(thirdResult.resultString)
    thirdMoveComparisonResult.setImageResource(thirdResult.resultImage)
    val endResult: MoveResult.Results.ChainResult = chainCompareResult(results)

    when (endResult) {
        playerWin -> Players.opponentHealth -= playerChain.totalDamage
        opponentWin -> Players.playerHealth -= opponentChain.totalDamage
    }
    when (endResult) {
        playerWin -> endResult.resultTotalDamage = playerChain.totalDamage
        opponentWin -> endResult.resultTotalDamage = opponentChain.totalDamage
    }

    return endResult
}

fun moveEnd(finalResult: FinalResultBinding, moveResults: MoveResultsBinding, uiObj: List<Any>) {

    Log.v(ContentValues.TAG, "##############################################")
    Log.v(TAG, "${chainCompareResult(results).resultTotalDamage} is the results total damage")
    Log.v(ContentValues.TAG, "##############################################")

    object : CountDownTimer(1000, 200) {

        // Callback function, fired on regular interval
        override fun onTick(millisUntilFinished: Long) {
            moveResults.moveResultsSet.visibility = View.GONE
            (chainCompareResult(results).resultChainString + ", DEALING " + chainCompareResult(
                results
            ).resultTotalDamage + " DAMAGE!").also { finalResult.finalResultText.text = it }
            finalResult.root.visibility = View.VISIBLE
        }

        // Callback function, fired when the time is up
        override fun onFinish() {
            moveResults.moveResultsSet.visibility = View.VISIBLE
            finalResult.root.visibility = View.GONE
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

    val moveButtons = uiObj[28] as MoveButtonsBinding

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

    "Opponent's First Move".also { oFirstMoveTextView.text = it }
    "Opponent's Second Move".also { oSecondMoveTextView.text = it }
    "Opponent's Third Move".also { oThirdMoveTextView.text = it }

    "Player's First Move".also { pFirstMoveTextView.text = it }
    "Player's Second Move".also { pSecondMoveTextView.text = it }
    "Player's Third Move".also { pThirdMoveTextView.text = it }

    Players.pChain.chainList.clear()
    Players.oChain.chainList.clear()

    moveButtons.root.visibility = View.VISIBLE
    moveButtons.kickButton.visibility = View.VISIBLE
    moveButtons.punchButton.visibility = View.VISIBLE
    moveButtons.grabButton.visibility = View.VISIBLE

    Players.pChain.moveSetStr = ""
    Players.oChain.moveSetStr = ""

    results.clear()
}