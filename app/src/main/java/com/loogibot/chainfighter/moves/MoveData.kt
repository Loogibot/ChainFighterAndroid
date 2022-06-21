package com.loogibot.chainfighter.moves

class MoveData {

    val kick = Move("kick", 25, "punch", "shield")
    val grab = Move("grab", 5, "kick", "shield")
    val dodge = Move("dodge", 5, "kick", "grab")
    val shield = Move("shield", 5, "punch", "dodge")
    var punch = Move("punch", 15, "grab", "dodge")

}