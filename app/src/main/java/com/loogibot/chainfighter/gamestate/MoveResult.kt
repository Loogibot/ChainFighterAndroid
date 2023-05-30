package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.R.drawable.cancel_image
import com.loogibot.chainfighter.R.drawable.neutral_result
import com.loogibot.chainfighter.R.drawable.opponent_move_win
import com.loogibot.chainfighter.R.drawable.player_move_win

class MoveResult {
    companion object Results {
        data class ChainResult(val resultImage: Int, val resultString: String)

        val neutral = ChainResult(neutral_result, "NEUTRAL")
        val cancel = ChainResult(cancel_image, "MOVES CANCEL EACH OTHER!")
        val playerWin = ChainResult(player_move_win, "PLAYER MOVE IS EFFECTIVE!")
        val opponentWin = ChainResult(opponent_move_win, "OPPONENT MOVE IS EFFECTIVE!")
    }
}
