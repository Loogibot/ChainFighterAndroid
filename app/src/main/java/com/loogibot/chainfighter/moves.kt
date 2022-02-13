package com.loogibot.chainfighter

data class move(val name: String, val damage: Int, val firstAdv: String, val secondAdv: String) {

    val kick = listOf(25, "punch", "shield")
    val punch = listOf(15, "grab", "dodge")
    val grab = listOf(5, "kick", "shield")
    val dodge = listOf(0, "kick", "grab")
    val shield = listOf(5, "punch", "dodge")



}
