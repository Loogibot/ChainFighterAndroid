package com.loogibot.chainfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerButtonOne: Button = findViewById(R.id.moveOne)
        playerButtonOne.setOnClickListener {moveChoice(1)}

        val playerButtonTwo: Button = findViewById(R.id.moveTwo)
        playerButtonTwo.setOnClickListener {moveChoice(2)}
    }


    private fun moveChoice(movePos: Int) {

        val opponentMove = MoveAvailable(3)
        val opponentChoice = opponentMove.moveCycle()
        val opponentImage: ImageView = findViewById(R.id.opponentChoice)

        var playerMove = MoveAvailable(movePos)
        var playerChoice = playerMove.moveCycle()

        val playerImage: ImageView = findViewById(R.id.playerChoice)

        val drawPlayerMove = when (playerChoice) {
            kick -> R.drawable.player_kick
            punch -> R.drawable.player_punch
            dodge -> R.drawable.player_dodge
            grab -> R.drawable.player_grab
            else -> R.drawable.player_shield
        }

        playerImage.setImageResource(drawPlayerMove)
        opponentChoice

    }
}

class MoveAvailable(private val movePos: Int)  {

    fun moveCycle() {
        if (movePos == 1) {}
        if (movePos == 2) {(1..5).random()}
        if (movePos == 3) {(1..5).random()}
    }
}

data class move(val name: String, val damage: Int, val firstAdv: String, val secondAdv: String) {

    val kick = move("kick",25, "punch", "shield")
    val punch = listOf("punch",15, "grab", "dodge")
    val grab = listOf("grab",5, "kick", "shield")
    val dodge = listOf("dodge",0, "kick", "grab")
    val shield = listOf("shield",5, "punch", "dodge")

}
