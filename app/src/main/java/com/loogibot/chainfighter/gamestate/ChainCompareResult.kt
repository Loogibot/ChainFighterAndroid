package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.ui.MoveResult

fun chainCompareResult(resultsList: ArrayList<String>): String {
    resultsList.removeAll(listOf(MoveResult.cancel.resultString).toSet())
    var finalResult = MoveResult.neutral.resultString

    if (resultsList.count { it == MoveResult.playerWin.resultString } >= 1) {
        finalResult = MoveResult.playerWin.resultString
    } else if (resultsList.count { it == MoveResult.opponentWin.resultString } >= 1) {
        finalResult = MoveResult.opponentWin.resultString
    } else {
        finalResult = MoveResult.cancel.resultString
    }
    return finalResult
}