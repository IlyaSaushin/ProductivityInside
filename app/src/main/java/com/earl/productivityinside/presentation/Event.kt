package com.earl.productivityinside.presentation

class Event<T>(private val data: T) {

    private var handled = false

    val value: T? get() {
        if (handled) return null
        handled = true
        return data
    }
}