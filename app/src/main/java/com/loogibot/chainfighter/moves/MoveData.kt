package com.loogibot.chainfighter.moves

// sets the move properties

class MoveData : MoveSource() {
    val kick = Move(kickStr, 25, punchStr, shieldStr, kickImg)
    val grab = Move(grabStr, 5, kickStr, shieldStr, grabImg)
    val dodge = Move(dodgeStr, 5, kickStr, grabStr, dodgeImg)
    val shield = Move(shieldStr, 5, punchStr, dodgeStr, shieldImg)
    var punch = Move(punchStr, 15, grabStr, dodgeStr, punchImg)
}