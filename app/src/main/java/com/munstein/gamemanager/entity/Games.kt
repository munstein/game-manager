package com.munstein.gamemanager.entity

data class Games(
    val have: ArrayList<String> = arrayListOf(),
    val want: ArrayList<String> = arrayListOf(),
    val playing: ArrayList<String> = arrayListOf()
)