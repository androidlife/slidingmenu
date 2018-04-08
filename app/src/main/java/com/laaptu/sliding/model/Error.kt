package com.laaptu.sliding.model

class Error(val type: Type) : Error() {
    enum class Type {
        Fetch,
        Network
    }
}
