package com.munstein.gamemanager.model

data class Games(
    val have: ArrayList<String> = arrayListOf(),
    val want: ArrayList<String> = arrayListOf(),
    val playing: ArrayList<String> = arrayListOf()
)