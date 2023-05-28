package com.loogibot.chainfighter.moves

// sets the move properties

class MoveData : MoveSource() {
    // (name of the move,
    // how much damage it can cause,
    // the first move weak to the named move,
    // the second move weak to the named move,
    // how much is costs to use the named move)

    val kick = Move(kickStr, 25, punchStr, shieldStr, kickImg, 2)
    val punch = Move(punchStr, 15, grabStr, dodgeStr, punchImg, 2)
    val grab = Move(grabStr, 5, kickStr, shieldStr, grabImg, 1)
    val dodge = Move(dodgeStr, 5, kickStr, grabStr, dodgeImg, 1)
    val shield = Move(shieldStr, 5, punchStr, dodgeStr, shieldImg, 0)

    // val none = Move("none", 0, "none", "none", 0, 0)
}