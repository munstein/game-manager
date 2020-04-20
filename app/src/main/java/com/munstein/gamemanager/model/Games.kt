package com.munstein.gamemanager.model

data class Games(val have: List<String> = listOf(),
                 val want: List<String> = listOf(),
                 val playing: List<String> = listOf())