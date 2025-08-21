package com.example.readerapp.data

class DataOrException<T, Boolean, E> {
    var data: T? = null
    var loading: kotlin.Boolean = false
    var e: E? = null

    override fun toString(): String {
        return "DataOrException(data= $data \nloading= $loading \ne=$e)"
    }
}