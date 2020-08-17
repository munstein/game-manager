package com.munstein.gamemanager.exceptions

import java.lang.Exception

class PlatformAlreadyExistsException : Exception() {
    override val message: String?
        get() = "Platform already exists."
}