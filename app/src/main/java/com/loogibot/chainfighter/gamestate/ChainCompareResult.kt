package com.loogibot.chainfighter.gamestate

fun chainCompareResult(resultsList: ArrayList<String>): String {
    resultsList.removeAll(listOf(MoveResult.cancel.resultString).toSet())

    val finalResult: String =
        if (resultsList.count { it == MoveResult.opponentWin.resultString } == resultsList.count { it == MoveResult.playerWin.resultString }) {
            MoveResult.cancel.resultChainString
        } else if (resultsList.count { it == MoveResult.playerWin.resultString } > resultsList.count { it == MoveResult.opponentWin.resultString }) {
            MoveResult.playerWin.resultChainString
        } else if (resultsList.count { it == MoveResult.opponentWin.resultString } > resultsList.count { it == MoveResult.playerWin.resultString }) {
            MoveResult.opponentWin.resultChainString
        } else {
            MoveResult.cancel.resultChainString
        }
    return finalResult
}