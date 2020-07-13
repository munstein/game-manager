package com.munstein.gamemanager.ui.mygames

enum class GameColumnEnum(val value: Int) {
    HAVE(0),
    PLAYING(1),
    WANT(2);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}