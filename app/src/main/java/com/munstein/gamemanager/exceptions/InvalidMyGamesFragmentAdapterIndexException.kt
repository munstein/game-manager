package com.munstein.gamemanager.exceptions

import java.lang.Exception

class InvalidMyGamesFragmentAdapterIndexException : Exception() {
    override val message: String?
        get() = "Can't crate fragment at invalid index position."
}