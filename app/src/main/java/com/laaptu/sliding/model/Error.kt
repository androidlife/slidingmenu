package com.laaptu.sliding.model

class Error(val type: Type) {
    enum class Type {
        Fetch,
        Network
    }
}
