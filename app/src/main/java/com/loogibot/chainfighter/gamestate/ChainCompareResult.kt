package com.loogibot.chainfighter.gamestate

fun chainCompareResult(resultsList: ArrayList<String>): String {
    resultsList.removeAll(listOf(MoveResult.cancel.resultString).toSet())

    val finalResult: String =
        if (resultsList.count { it == MoveResult.opponentWin.resultString } == resultsList.count { it == MoveResult.playerWin.resultString }) {
            MoveResult.cancel.resultString
        } else if (resultsList.count { it == MoveResult.playerWin.resultString } >= 1) {
            MoveResult.playerWin.resultString
        } else if (resultsList.count { it == MoveResult.opponentWin.resultString } >= 1) {
            MoveResult.opponentWin.resultString
        } else {
            MoveResult.cancel.resultString
        }
    return finalResult
}