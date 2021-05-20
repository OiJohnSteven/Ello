package com.intellect.ello

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}