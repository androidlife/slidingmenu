package com.laaptu.sliding.model

import kotlin.Error

class Error(val type: Type) : Error() {
    enum class Type {
        Fetch,
        Network
    }
}
