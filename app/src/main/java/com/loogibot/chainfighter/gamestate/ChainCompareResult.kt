package com.loogibot.chainfighter.gamestate

fun chainCompareResult(resultsList: ArrayList<String>): MoveResult.Results.ChainResult {
    resultsList.removeAll(listOf(MoveResult.cancel.resultString).toSet())

    val finalResult: MoveResult.Results.ChainResult =
        if (resultsList.count { it == MoveResult.opponentWin.resultString } == resultsList.count { it == MoveResult.playerWin.resultString }) {
            MoveResult.cancel
        } else if (resultsList.count { it == MoveResult.playerWin.resultString } > resultsList.count { it == MoveResult.opponentWin.resultString }) {
            MoveResult.playerWin
        } else if (resultsList.count { it == MoveResult.opponentWin.resultString } > resultsList.count { it == MoveResult.playerWin.resultString }) {
            MoveResult.opponentWin
        } else {
            MoveResult.cancel
        }
    return finalResult
}
