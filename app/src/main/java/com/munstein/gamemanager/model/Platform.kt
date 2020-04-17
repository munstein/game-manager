package com.munstein.gamemanager.model

data class Platform(val title: String, val games : Games)

data class Games(val have: List<String> = listOf(),
                 val want: List<String> = listOf(),
                 val playing: List<String> = listOf())