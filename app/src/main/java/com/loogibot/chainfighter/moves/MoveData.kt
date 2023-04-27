package com.loogibot.chainfighter.moves

// sets the move properties

class MoveData : MoveSource() {

    val kick = Move(kickStr, 25, punchStr, shieldStr, kickImg, 2)
    val grab = Move(grabStr, 5, kickStr, shieldStr, grabImg, 1)
    val dodge = Move(dodgeStr, 5, kickStr, grabStr, dodgeImg, 1)
    val shield = Move(shieldStr, 5, punchStr, dodgeStr, shieldImg, 0)
    val punch = Move(punchStr, 15, grabStr, dodgeStr, punchImg, 2)

    // val none = Move("none", 0, "none", "none", 0, 0)
}