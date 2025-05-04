package com.loogibot.chainfighter.ui

import android.content.ContentValues.TAG
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.loogibot.chainfighter.databinding.FinalResultBinding
import com.loogibot.chainfighter.databinding.MoveButtonsBinding
import com.loogibot.chainfighter.databinding.MoveResultsBinding
import com.loogibot.chainfighter.databinding.OpponentViewBinding
import com.loogibot.chainfighter.databinding.PlayerViewBinding
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
    val moveResults = uiObj[0] as MoveResultsBinding
    val opponentView = uiObj[1] as OpponentViewBinding
    val playerView = uiObj[2] as PlayerViewBinding

    if (playerView.PFirstMoveImg.drawable == null) {
        firstMoveInChain(
            opponentView, playerView, playerChain, opponentChain, moveResults
        )
    } else if (playerView.PSecondMoveImg.drawable == null) {
        secondMoveInChain(
            opponentView, playerView, playerChain, opponentChain, moveResults
        )
    } else {
        endResult = thirdMoveInChain(
            opponentView, playerView, playerChain, opponentChain, moveResults
        )
        object : CountDownTimer(1000, 200) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                // pause
                "PLAYER HP: ${Players.playerHealth}".also { playerView.playerHPText.text = it }
                "OPPONENT HP: ${Players.opponentHealth}".also {
                    opponentView.opponentHPText.text = it
                }
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                moveEnd(moveResults.finalResult, moveResults, uiObj)
                playerView.playerHPBar.progress = Players.playerHealth
                opponentView.opponentHPBar.progress = Players.opponentHealth
            }
        }.start()
        playerChain.chainCost = 0
        opponentChain.chainCost = 0
    }
    return endResult
}

fun firstMoveInChain(
    opponentView: OpponentViewBinding,
    playerView: PlayerViewBinding,
    playerChain: Chain,
    opponentChain: Chain,
    moveResults: MoveResultsBinding
) {
    playerView.PFirstMoveImg.setImageResource(playerChain.firstMove.moveImg)
    opponentView.OFirstMoveImg.setImageResource(opponentChain.firstMove.moveImg)

    val firstResult = moveCompare(playerChain.firstMove, opponentChain.firstMove)
    moveResults.firstResult.text = firstResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerView.playerChainCost.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also {
        opponentView.opponentChainCost.text = it
    }

    "${playerChain.firstMove.name} costs ${playerChain.firstMove.cost}, deals ${playerChain.firstMove.damage} damage ".also {
        playerView.PFirstMoveTitle.text = it
    }

    "${opponentChain.firstMove.name} costs ${opponentChain.firstMove.cost}, deals ${opponentChain.firstMove.damage} damage ".also {
        opponentView.OFirstMoveTitle.text = it
    }

    results.add(firstResult.resultString)
    moveResults.FirstMoveImgResult.setImageResource(firstResult.resultImage)
}

fun secondMoveInChain(
    opponentView: OpponentViewBinding,
    playerView: PlayerViewBinding,
    playerChain: Chain,
    opponentChain: Chain,
    moveResults: MoveResultsBinding
) {
    playerView.PSecondMoveImg.setImageResource(playerChain.secondMove.moveImg)
    opponentView.OSecondMoveImg.setImageResource(opponentChain.secondMove.moveImg)

    val secondResult = moveCompare(playerChain.secondMove, opponentChain.secondMove)
    moveResults.secondResult.text = secondResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerView.playerChainCost.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also {
        opponentView.opponentChainCost.text = it
    }

    (playerChain.secondMove.name + " costs " + playerChain.secondMove.cost + ", deals " + playerChain.secondMove.damage + " damage ").also {
        playerView.PSecondMoveTitle.text = it
    }

    (opponentChain.secondMove.name + " costs " + opponentChain.secondMove.cost + ", deals " + opponentChain.secondMove.damage + " damage ").also {
        opponentView.OSecondMoveTitle.text = it
    }

    results.add(secondResult.resultString)
    moveResults.SecondMoveImgResult.setImageResource(secondResult.resultImage)
}

fun thirdMoveInChain(
    opponentView: OpponentViewBinding,
    playerView: PlayerViewBinding,
    playerChain: Chain,
    opponentChain: Chain,
    moveResults: MoveResultsBinding
): MoveResult.Results.ChainResult {
    playerView.PThirdMoveImg.setImageResource(playerChain.thirdMove.moveImg)
    opponentView.OThirdMoveImg.setImageResource(opponentChain.thirdMove.moveImg)

    val thirdResult = moveCompare(playerChain.thirdMove, opponentChain.thirdMove)
    moveResults.thirdResult.text = thirdResult.resultString

    "PLAYER CHAIN COST: ${playerChain.chainCost}/6".also { playerView.playerChainCost.text = it }
    "OPPONENT CHAIN COST: ${opponentChain.chainCost}/6".also {
        opponentView.opponentChainCost.text = it
    }

    (playerChain.thirdMove.name + " costs " + playerChain.thirdMove.cost + ", deals " + playerChain.thirdMove.damage + " damage ").also {
        playerView.PThirdMoveTitle.text = it
    }

    (opponentChain.thirdMove.name + " costs " + opponentChain.thirdMove.cost + ", deals " + opponentChain.thirdMove.damage + " damage ").also {
        opponentView.OThirdMoveTitle.text = it
    }

    results.add(thirdResult.resultString)
    moveResults.ThirdMoveImgResult.setImageResource(thirdResult.resultImage)
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

    Log.v(TAG, "##############################################")
    Log.v(TAG, "${chainCompareResult(results).resultTotalDamage} is the results total damage")
    Log.v(TAG, "##############################################")

    object : CountDownTimer(1000, 100) {

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

    val moveResults = uiObj[0] as MoveResultsBinding
    val opponentView = uiObj[1] as OpponentViewBinding
    val playerView = uiObj[2] as PlayerViewBinding
    val moveButtonsView = uiObj[3] as MoveButtonsBinding

    moveResults.firstResult.text = ""
    moveResults.secondResult.text = ""
    moveResults.thirdResult.text = ""

    opponentView.OFirstMoveImg.setImageResource(0)
    opponentView.OSecondMoveImg.setImageResource(0)
    opponentView.OThirdMoveImg.setImageResource(0)
    playerView.PFirstMoveImg.setImageResource(0)
    playerView.PSecondMoveImg.setImageResource(0)
    playerView.PThirdMoveImg.setImageResource(0)

    moveResults.FirstMoveImgResult.setImageResource(0)
    moveResults.SecondMoveImgResult.setImageResource(0)
    moveResults.ThirdMoveImgResult.setImageResource(0)

    "Opponent's First Move".also { opponentView.OFirstMoveTitle.text = it }
    "Opponent's Second Move".also { opponentView.OSecondMoveTitle.text = it }
    "Opponent's Third Move".also { opponentView.OThirdMoveTitle.text = it }

    "Player's First Move".also { playerView.PFirstMoveTitle.text = it }
    "Player's Second Move".also { playerView.PSecondMoveTitle.text = it }
    "Player's Third Move".also { playerView.PThirdMoveTitle.text = it }

    Players.pChain.chainList.clear()
    Players.oChain.chainList.clear()

    moveButtonsView.root.visibility = View.VISIBLE
    moveButtonsView.kickButton.visibility = View.VISIBLE
    moveButtonsView.punchButton.visibility = View.VISIBLE
    moveButtonsView.grabButton.visibility = View.VISIBLE

    Players.pChain.moveSetStr = ""
    Players.oChain.moveSetStr = ""

    results.clear()
}