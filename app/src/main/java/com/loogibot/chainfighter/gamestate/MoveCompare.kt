package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.moves.Move
import com.loogibot.chainfighter.ui.MoveResult

fun moveCompare(playerMove: Move, opponentMove: Move): MoveResult.Results.ChainResult {

    var result = MoveResult.neutral
    if (playerMove != opponentMove) {
        //opponent move is successful
        if (playerMove.name == opponentMove.firstAdv || playerMove.name == opponentMove.secondAdv) {
            result = MoveResult.opponentWin
        } else if (opponentMove.name == playerMove.firstAdv || opponentMove.name == playerMove.secondAdv) {
            result = MoveResult.playerWin
        }
    } else {
        return MoveResult.cancel
    }
    return result
}