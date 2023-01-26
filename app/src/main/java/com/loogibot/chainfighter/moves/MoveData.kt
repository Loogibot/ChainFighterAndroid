package com.loogibot.chainfighter.moves

// sets the move properties
class MoveData : MoveAdapter() {

    val kick = Move(kickStr, 25, punchStr, shieldStr)
    val grab = Move(grabStr, 5, kickStr, shieldStr)
    val dodge = Move(dodgeStr, 5, kickStr, grabStr)
    val shield = Move(shieldStr, 5, punchStr, dodgeStr)
    var punch = Move(punchStr, 15, grabStr, dodgeStr)
}