package com.loogibot.chainfighter.gamestate

import com.loogibot.chainfighter.R.drawable.cancel_image
import com.loogibot.chainfighter.R.drawable.neutral_result
import com.loogibot.chainfighter.R.drawable.opponent_move_win
import com.loogibot.chainfighter.R.drawable.player_move_win
import com.loogibot.chainfighter.player.Players

class MoveResult {
    companion object Results {
        data class ChainResult(
            val resultImage: Int,
            val resultString: String,
            val resultChainString: String,
            var resultTotalDamage: Int
        )

        val neutral =
            ChainResult(
                neutral_result,
                "NEUTRAL",
                "NEUTRAL",
                0

            )
        val cancel =
            ChainResult(
                cancel_image,
                "MOVES CANCEL EACH OTHER!",
                "ALL MOVES CANCEL EACH OTHER!", 0
            )
        val playerWin =
            ChainResult(
                player_move_win,
                "PLAYER MOVE IS EFFECTIVE!",
                "PLAYER CHAIN ",
                Players.pChain.totalDamage
            )
        val opponentWin =
            ChainResult(
                opponent_move_win,
                "OPPONENT MOVE IS EFFECTIVE!",
                "OPPONENT CHAIN ",
                Players.oChain.totalDamage
            )
    }
}
