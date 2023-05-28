package com.loogibot.chainfighter.ui

import com.loogibot.chainfighter.R.drawable.cancel_image
import com.loogibot.chainfighter.R.drawable.neutral_result
import com.loogibot.chainfighter.R.drawable.opponent_move_win
import com.loogibot.chainfighter.R.drawable.player_move_win

class MoveResult {
    companion object Results {

        const val neutralResult = neutral_result
        const val cancelResult = cancel_image
        const val playerMoveWinResult = player_move_win
        const val opponentMoveWinResult = opponent_move_win
    }
}