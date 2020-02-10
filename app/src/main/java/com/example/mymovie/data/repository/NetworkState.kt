package com.example.mymovie.data.repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status,val msg: String) {

    //we use companion object when we want to use static
    companion object{
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        var ENDOFLIST : NetworkState
        //when wanting to use constructor
        init {
            LOADED = NetworkState(Status.SUCCESS,"Success")
            LOADING = NetworkState(Status.RUNNING,"Running")
            ERROR = NetworkState(Status.FAILED,"Something went wrong")
            ENDOFLIST = NetworkState(Status.FAILED,"You Have reached the end")
        }
    }
}